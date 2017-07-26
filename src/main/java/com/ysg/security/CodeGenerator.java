package com.ysg.security;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * Created by Thaslim on 23/05/17.
 */
@Service
public class CodeGenerator {

    private static final int PIN_SIZE = 6;
    private static final int MIL = 1000000;
    private static final int TENTH_BIL = 100000000;

    private SecureRandom random;

    @PostConstruct
    public void init() {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    public String generate(String prefix) {

        byte[] bytes = new byte[8];
        random.nextBytes(bytes);

        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, bytes.length);

        random.nextBytes(bytes);
        checksum.update(bytes, 0, bytes.length);
        checksum.update(prefix.getBytes(), 0, prefix.length());

        long checksumValue = checksum.getValue();
        long code = checksumValue % TENTH_BIL;

        String part = String.format("%s%08d", prefix.toUpperCase(), code);
        int d = checkDigit(part);

        return part + d;
    }

    public int checkDigit(String id) {

        int sum = 0;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);

            int val = 0;
            if (id.charAt(i) >= 'A' && id.charAt(i) <= 'Z')
                val = c - 'A' + 1;
            else
                val = c - '0';

            sum += (i % 2 == 0) ? val : val * 2;
        }

        return (sum * 9) % 10;
    }
}