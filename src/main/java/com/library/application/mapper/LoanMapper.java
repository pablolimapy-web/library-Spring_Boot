package com.library.application.mapper;

import com.library.domain.model.Loan;
import com.library.application.dto.LoanDTO;

public class LoanMapper {

    public static LoanDTO toDTO(Loan loan) {
        if (loan == null) return null;
        return new LoanDTO(
                loan.getId(),
                loan.getBook().getId(),
                loan.getUser().getId(),
                loan.getLoanDate(),
                loan.getReturnDate()
        );
    }
}
