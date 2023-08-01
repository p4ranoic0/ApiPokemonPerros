package com.isil.ExamenFinal.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class pokeResponse {
    private String name;
}
