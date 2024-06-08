package br.com.adotaaju.adotaajuapi.core;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private List<String> erros;

    public ErrorResponse(int status, String message){
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(int status, String message, List<String> erros) {
        this.message = message;
        this.status = status;
        this.erros = erros;
    }

}
