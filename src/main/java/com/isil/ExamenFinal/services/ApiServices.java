package com.isil.ExamenFinal.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isil.ExamenFinal.config.JwtException;
import com.isil.ExamenFinal.config.JwtProvider;
import com.isil.ExamenFinal.response.PerroResponse;
import com.isil.ExamenFinal.response.PokemonResponse;
import com.isil.ExamenFinal.shared.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ApiServices {
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RestTemplate restTemplate;


    private static String urlDogs="https://dog.ceo/api/breeds/list/all";
    public String generatedTokenPokemon(String username,String servicio) throws JwtException {
        if(!servicio.equals("") ){
            return jwtProvider.generatedTokenPokemon(username,servicio);
        }
        else{
            throw new JwtException(Constants.DATA_EMPTYPOKEMON,400);
        }
    }
    public String generatedTokenPerros(String username,String servicio) throws JwtException {
        if(!servicio.equals("") ){
            return jwtProvider.generatedTokenPerros(username,servicio);
        }
        else{
            throw new JwtException(Constants.DATA_EMPTYPERROS,400);
        }
    }

    public PokemonResponse getDataPokemon(String token){
        try {
           jwtProvider.readToken(token);
            int randomMaximo = ThreadLocalRandom.current().nextInt(700, 2000);
            int randomMinimo = ThreadLocalRandom.current().nextInt(1, 700);
            String urlPoke="https://pokeapi.co/api/v2/pokemon?limit="+randomMaximo+"&offset="+randomMinimo;
            PokemonResponse pokereponse = restTemplate.getForObject(urlPoke,PokemonResponse.class );
            return pokereponse;

        }catch (ExpiredJwtException e){
            throw  new JwtException(Constants.ERROR_MESSAGE_WHEN_JWT_EXPIRED,Constants.HTTP_CODE_JWT_EXPIRED);
        }
    }

    public PerroResponse getDataPerros(String token){
        try {
            jwtProvider.readToken(token);
        PerroResponse response = restTemplate.getForObject(urlDogs, PerroResponse.class );
        response.getMessage();
        return response;
        }catch (ExpiredJwtException e){
            throw  new JwtException(Constants.ERROR_MESSAGE_WHEN_JWT_EXPIRED,Constants.HTTP_CODE_JWT_EXPIRED);
        }
    }



}
