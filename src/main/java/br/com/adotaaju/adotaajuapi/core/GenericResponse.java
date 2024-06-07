package br.com.adotaaju.adotaajuapi.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int status;
    private String mensagem;
    private T dados;
}
