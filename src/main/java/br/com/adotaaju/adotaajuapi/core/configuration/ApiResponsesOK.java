package br.com.adotaaju.adotaajuapi.core.configuration;

import br.com.adotaaju.adotaajuapi.core.GenericResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({@ApiResponse(
        responseCode = "200",
        description = "Operação realizada com sucesso",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "400",
        description = "Parâmetros inválidos",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "403",
        description = "Proibido. O complemento da mensagem varia de acordo com a operação",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "404",
        description = "Não encontrado",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "405",
        description = "Método não suportado",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "429",
        description = "Não autorizado. Limite de uso excedido",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
), @ApiResponse(
        responseCode = "500",
        description = "Erro genérico. A mensagem varia de acordo com a operação",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = GenericResponse.class
                )
        )}
)})
public @interface ApiResponsesOK {
}
