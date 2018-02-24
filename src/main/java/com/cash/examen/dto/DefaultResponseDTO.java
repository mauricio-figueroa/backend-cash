package com.cash.examen.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder(toBuilder = true)
public class DefaultResponseDTO {

    private HttpStatus status;
    private String message;

}
