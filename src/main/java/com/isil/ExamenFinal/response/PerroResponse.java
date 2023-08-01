package com.isil.ExamenFinal.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerroResponse {
    Map<String,Object> message;
}
