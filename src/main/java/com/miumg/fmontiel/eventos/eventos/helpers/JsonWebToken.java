package com.miumg.fmontiel.eventos.eventos.helpers;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JsonWebToken {
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "012673nyanyusduy1237617279n43yasd87123mbzxcquowepop1o2397962834kxcvnbzmbnxqalquiw301o23a91q23lakjsdiuqyweyuasd019237109237192n03"
                    .getBytes());

    public static String generate(Integer id) {
        Map<String, Integer> claims = new HashMap<>();
        claims.put("usuario", id);

        return Jwts.builder()
                .signWith(SECRET_KEY)
                .claims()
                .add(claims)
                // 24 horas
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .toString();
    }

    public static void validate(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getEncoded()))
                    .build();
            /*
             * .parse(token)
             * //.decryptWith(Keys.hmacShaKeyFor(SECRET_KEY.getEncoded())
             * //.build()
             * .getBody(token);
             */

        } catch (Exception e) {

        }
    }

    public static void decode(String token) {
        try {
            String claims = Jwts.builder()
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getEncoded())) // #1
                    .content(token.getBytes(StandardCharsets.UTF_8)) // #2
                    .encodePayload(false) // #3
                    .compact();
            // .accept(Jwe.CLAIMS);

            System.out.println(claims);

        } catch (Exception e) {
            System.out.println("Error al extraer claims: " + e.getMessage());
        }
    }

}
