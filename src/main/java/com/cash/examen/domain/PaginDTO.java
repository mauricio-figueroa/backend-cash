package com.cash.examen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PaginDTO {

    private Integer offset;
    private Integer limit;
    private Integer total;
}
