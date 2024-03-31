package com.rosivaldolucas.picpaysimplificado.api.dto;

import java.math.BigDecimal;

public record NovaTransacaoInput(
        BigDecimal valor,
        Long pagador,
        Long recebedor
) {
}
