package com.isil.ExamenFinal.config;

import com.isil.ExamenFinal.response.PokemonResponse;
import com.isil.ExamenFinal.shared.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final byte[] signinKey;
    private final String SECRET_KEY = "secretPokmonsecretPokmonsecretPokmonsecretPokmonsecretPokmon";
    private final long tokenValidityInSeconds;
    private final String apiKeyPokemon = "d206438c-265d-411c-ba18-3b26687d82e6";

    private final String apiKeyPerros = "0ecc87d5-d5bd-4704-8d12-b9dd1df7e303";
    public JwtProvider(){
        this.signinKey = Decoders.BASE64.decode(SECRET_KEY);
        this.tokenValidityInSeconds=60;
    }

    public String generatedTokenPokemon(String username,String servicio){
        if (servicio.equals("pokemon")){
        Claims claims = new DefaultClaims();
        claims.setSubject(username);
        claims.put("servicio",servicio);
        claims.put("api-key",apiKeyPokemon);
           return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+tokenValidityInSeconds*1000))
                .signWith(Keys.hmacShaKeyFor(signinKey), SignatureAlgorithm.HS256)
                .compact();
        }
        return "Si desea usar el servicio pokemon, describalo con el paremetro servicio";
    }

    public String generatedTokenPerros(String username,String servicio){
        if (servicio.equals("perros")){
            Claims claims = new DefaultClaims();
            claims.setSubject(username);
            claims.put("servicio",servicio);
            claims.put("api-key",apiKeyPerros);
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+tokenValidityInSeconds*1000))
                    .signWith(Keys.hmacShaKeyFor(signinKey), SignatureAlgorithm.HS256)
                    .compact();
        }
        return "Si desea usar el servicio perros, describalo con el paremetro servicio";
    }

    public String readToken(String token){
        if (token.isBlank()){
            throw new JwtException (Constants.TOKEN_EMPTY,Constants.CODE_WHEN_EXCEPTION_TOKEN_NULL);
        }
        Jws<Claims> claimsJws= Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

}
