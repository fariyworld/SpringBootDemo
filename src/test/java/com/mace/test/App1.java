package com.mace.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * description:
 * <br />
 * Created by mace on 13:05 2018/6/25.
 */
public class App1 {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String pwd = "liuye0425";
//        $2a$10$n8iAWNartbvVTbvErFKiZOy3k0J.Ad2hCK1KiQUk1ZgxFmw41y08C
        System.out.println(encoder.encode(pwd));
    }
}
