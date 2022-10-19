package com.emresahna.loginwithreact.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecordRequest {
    private String title;
    private BigDecimal amount;
    private Long category_id;
}
