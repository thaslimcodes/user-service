package com.ysg.service;

import com.ysg.model.GoogleTokenResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jayaprakash on 04/05/17.
 */

@FeignClient(name = "google-token-service", url = "https://www.googleapis.com/")
public interface GoogleTokenService {

    @RequestMapping("oauth2/v3/tokeninfo")
    GoogleTokenResponse verify(@RequestParam("id_token") String token);
}
