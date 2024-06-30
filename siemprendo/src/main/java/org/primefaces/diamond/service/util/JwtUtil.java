package org.primefaces.diamond.service.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    // Reemplaza con tu clave secreta
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5978";

    public static String createJwt() {
        // Crea un JWT con una fecha de expiración de 1 hora
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String jwt = Jwts.builder()
                .setSubject("marlon.f.1993@gmail.com")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora en milisegundos
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public static boolean validateJwt(String jwt) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            // Verifica la expiración
            Date expiration = claimsJws.getBody().getExpiration();

            // La fecha de expiración no ha pasado
            return !expiration.before(new Date());
        } catch (Exception e) {
            // El token no es válido
            return false;
        }
    }

    public static void main(String[] args) {
        //String jwt = createJwt();
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1haWwuY29tIiwiaWF0IjoxNzA2OTgyODE4LCJleHAiOjE3MDcwNjkyMTh9.J5F2SPad0k0fK_w2J0jZJ5tobq0CYqdOmq_Zz-BDb1Y";
        System.out.println("JWT: " + jwt);

        boolean isValid = validateJwt(jwt);
        System.out.println("El JWT es válido: " + isValid);
    }
}
