package cn.org.sqx.mall.passport;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: sqx
 * @Date: 2022-09-21
 */
public class JwtTests {

    String secretKey = "mjqhrgx";

    @Test
    public void testGenerateJwt() {
        // Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527);
        claims.put("name", "小玉");

        // JWT的组成部分：Header（头），Payload（载荷），Signature（签名）
        String jwt = Jwts.builder()
                // Header：指定算法与当前数据类型
                // 格式为： { "alg": 算法, "typ": "jwt" }
                .setHeaderParam(Header.CONTENT_TYPE, "HS256")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                // Payload：通常包含Claims（自定义数据）和过期时间
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                // Signature：由算法和密钥（secret key）这2部分组成
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        // eyJjdHkiOiJIUzI1NiIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJuYW1lIjoi5bCP546JIiwiaWQiOjk1MjcsImV4cCI6MTY2Mzc2NTY1Mn0.Z_kUhsM6bAczXngOIkm5m_5CZc_bAdyMszOWmdzr4uI
        // eyJjdHkiOiJIUzI1NiIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJuYW1lIjoi5bCP546JIiwiaWQiOjk1MjcsImV4cCI6MTY2Mzc2NTY3M30.eZihVb2rUY2Dn4vE_kT1XsCqct7PMKfdmgQG_vM97QY

        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String jwt = "eyJjdHkiOiJIUzI1NiIsInR5cCI6IkpXVCIsImFsZyI6IkhTMjU2In0.eyJuYW1lIjoi5bCP546JIiwiaWQiOjk1MjcsImV4cCI6MTY2Mzc2NTY1Mn0.Z_kUhsM6bAczXngOIkm5m_5CZc_bAdyMszOWmdzr4uI";
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        Object id = claims.get("id");
        Object name = claims.get("name");
        System.out.println("id=" + id);
        System.out.println("name=" + name);


    }
}
