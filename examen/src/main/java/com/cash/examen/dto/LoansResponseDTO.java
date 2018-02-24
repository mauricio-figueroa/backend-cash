package com.cash.examen.dto;

import com.cash.examen.domain.Loan;
import com.cash.examen.domain.PaginDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoansResponseDTO {

    private List<Loan> items;
    private PaginDTO pagin;
}
