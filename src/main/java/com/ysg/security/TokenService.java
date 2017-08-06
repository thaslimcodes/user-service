package com.ysg.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ysg.data.City;
import com.ysg.model.UserScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Thaslim on 11/03/17.
 */
@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private String BEARER = "Bearer";

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Value("${jwt.claim.user}")
    private String userClaim;

    @Value("${jwt.claim.scopes}")
    private String scopesClaim;

    @Value("${jwt.token.expiry}")
    private int tokenExpiry;

    // TODO: Change to dynamic
    private String SECRET = "iota@123";

    public String generate(String user, String[] audience, String[] scopes, List<City> cityList) {
        try {
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(twoWeeks())
                    .withAudience(audience)
                    .withClaim(userClaim, user)
                    .withArrayClaim(scopesClaim, scopes)
                    .sign(Algorithm.HMAC512(SECRET));

            return token;
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            logger.error("Unable to generate token. {}", e.getLocalizedMessage());
        }

        return null;
    }

    public UserScope verify(String header) {

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET))
                    .withIssuer(issuer)
                    .build();

            String token = parseToken(header);
            logger.info("Parsed: {}", token);

            DecodedJWT jwt = verifier.verify(token);

            List<String> audiences = jwt.getAudience();
            Optional<String> matched = audiences.stream().filter(a -> a.equals(audience)).findFirst();
            if (!matched.isPresent()) {
                logger.error("Token is not intended for this server");
                return null;
            }

            UserScope us = new UserScope();

            Claim user = jwt.getClaim(userClaim);
            if (!user.isNull())
                us.setUser(user.asString());

            Claim scopes = jwt.getClaim(scopesClaim);
            if (!scopes.isNull()) {
                List<String> userScopes = scopes.asList(String.class);
                us.setRoles(userScopes);
            }

            return us;
        } catch (JWTVerificationException | UnsupportedEncodingException e) {
            logger.error("Unable to verify {}", e.getLocalizedMessage());
        }

        return null;
    }

    private Date twoWeeks() {
        return new Date(System.currentTimeMillis() + tokenExpiry * 24 * 60 * 60 * 1000);
    }

    public String parseToken(String header) {

        int start = header.indexOf(BEARER);
        if (start < 0)
            return header;

        return header.substring(start + BEARER.length() + 1);
    }
}
