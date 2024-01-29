package com.water.can.WaterCanal.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public GenericResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public GenericResponse() {

    }

    public GenericResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

// Additional methods if needed
}