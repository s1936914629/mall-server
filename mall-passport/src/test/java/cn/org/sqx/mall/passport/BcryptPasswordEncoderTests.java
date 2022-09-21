package cn.org.sqx.mall.passport;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
public class BcryptPasswordEncoderTests {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testEncode(){
        // 原文相同的情况，每次加密得到的密文都不同
        for (int i = 0; i < 10; i++) {
            String rawPassword = "123456";
            String encode = passwordEncoder.encode(rawPassword);
            System.out.println("rawPassword = " + rawPassword);
            System.out.println("encodedPassword = " + encode);
            // rawPassword = 123456
            // encodedPassword = $2a$10$bwCmQLNqIXNNlD0HbU6jvuNsxAtTGiOYPcYeyLoE9MXmEmyp3Gurq
            // encodedPassword = $2a$10$Y3cGrAYVcSazn.MbpirbseH1lC4JZJpNAtOnLtGU/CUOSJ28Zf1bK
            // encodedPassword = $2a$10$aS1ItQ6.wFJeIAW8Tpr9s.yXEFgByBLVtYfDwJhsdiCJFSwN1Uo..
            // encodedPassword = $2a$10$eWEO0tinvHxj5LFYLdYo3.N4rzJjeTSEiKN2geBk1feqh8T7i3nOq
            // encodedPassword = $2a$10$jYvXLLxPrpKwoR.kccEemuRgamfs.NEZ1PQ93It36EaFnlvFJgB.m
            // encodedPassword = $2a$10$ZZ3LTbgUdUvudexarUhYN.4ZNTJbiAUIE7KwThrb8Us2d/5s8uMxu
            // encodedPassword = $2a$10$YVIXb5yOmiFz1T2UrlAqF.xNn.CXADzoyOQ9ofwEjIGyiSwwUYJV6
            // encodedPassword = $2a$10$NbbYHJmyKMCIIxrT23PEz.Z/u9GmMTTEHs8WEquvjD7Ro.BumqCBy
            // encodedPassword = $2a$10$HWNeB.ueaZfo22Hub2xCa.6lO7qFpvDKanm1SXlOokE9.vmFcmgWu
            // encodedPassword = $2a$10$iSirF34uKfvbnlbX5A.7LusdYcSgJTuW0O4wxVba6uf4zlcb.84g6
        }
    }

    @Test
    public void testMatches(){
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$bwCmQLNqIXNNlD0HbU6jvuNsxAtTGiOYPcYeyLoE9MXmEmyp3Gurq";
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("match result : " + matches);

    }

}
