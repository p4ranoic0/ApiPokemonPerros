package com.isil.ExamenFinal.controller;

import com.isil.ExamenFinal.config.JwtException;
import com.isil.ExamenFinal.services.ApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/v1")
public class ApiController {

@Autowired
private ApiServices services;

    //http://localhost:8080/api/v1/pokemon/token?username=luis@gmail.com&servicio=pokemon
    @GetMapping("pokemon/token")
    public ResponseEntity<String> tokenPokemon (@RequestParam String username, @RequestParam String servicio)throws JwtException {
        try {
            return ResponseEntity.ok().body(services.generatedTokenPokemon(username,servicio));
        } catch (JwtException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    //http://localhost:8080/api/v1/perros/token?username=luis@gmail.com&servicio=perros
    @GetMapping("perros/token")
    public ResponseEntity<String> tokenPerros (@RequestParam String username, @RequestParam String servicio)throws JwtException {
        try {
            return ResponseEntity.ok().body(services.generatedTokenPerros(username,servicio));
        } catch (JwtException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }
    //http://localhost:8080/api/v1/pokemon/get-all
    @RequestMapping("/pokemon/get-all")
    public ResponseEntity<HashMap<String,Object>> getAllPokemons(@RequestHeader("Authorization") String authorization)throws JwtException {
        HashMap<String,Object> response= new HashMap<>();
        try {
            response.put("response",(Object) services.getDataPokemon(authorization));
            return ResponseEntity.ok().body(response);
        }catch (JwtException e) {
            response.put("Error",e.getMessage());
            return ResponseEntity.status(e.getCode()).body(response);
        }

    }
    //http://localhost:8080/api/v1/perros/razas
    @GetMapping
    @RequestMapping("/perros/razas")
    public ResponseEntity<HashMap<String,Object>> getAllPerros(@RequestHeader("Authorization") String authorization)throws JwtException{
        HashMap<String,Object> response= new HashMap<>();
        try {
            response.put("response",(Object) services.getDataPerros(authorization));
            return ResponseEntity.ok().body(response);
        }catch (JwtException e) {
            response.put("Error",e.getMessage());
            return ResponseEntity.status(e.getCode()).body(response);
        }
    }

}
