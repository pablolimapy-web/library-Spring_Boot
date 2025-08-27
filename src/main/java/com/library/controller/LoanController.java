package com.library.controller;

import com.library.application.dto.LoanDTO;
import com.library.application.dto.LoanRequestDTO;
import com.library.application.mapper.LoanMapper;
import com.library.domain.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanRequestDTO request) {
        try {
            LoanDTO loanDTO = LoanMapper.toDTO(
                    loanService.createLoan(request.getBookId(), request.getUserId())
            );
            return ResponseEntity.ok(loanDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable("id") UUID id) {
        try {
            LoanDTO loanDTO = LoanMapper.toDTO(loanService.returnBook(id));
            return ResponseEntity.ok(loanDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/active")
    public List<LoanDTO> getActiveLoans() {
        return loanService.getActiveLoans()
                .stream()
                .map(LoanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/active/user/{userId}")
    public ResponseEntity<?> getActiveLoansByUser(@PathVariable("userId") UUID userId) {
        try {
            List<LoanDTO> loans = loanService.getActiveLoansByUser(userId)
                    .stream()
                    .map(LoanMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(loans);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/active/book/{bookId}")
    public ResponseEntity<?> getActiveLoansByBook(@PathVariable("bookId") UUID bookId) {
        try {
            List<LoanDTO> loans = loanService.getActiveLoansByBook(bookId)
                    .stream()
                    .map(LoanMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(loans);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
