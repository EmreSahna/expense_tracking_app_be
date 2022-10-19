package com.emresahna.loginwithreact.Dto;

import com.emresahna.loginwithreact.Entity.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RecordResponse {
    private Long id;
    private String title;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Category category;
}
