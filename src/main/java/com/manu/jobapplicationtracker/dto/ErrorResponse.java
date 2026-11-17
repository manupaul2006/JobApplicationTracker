package com.manu.jobapplicationtracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ErrorResponse {
   public ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
        this.fieldErrors = new HashMap<>();
    }
    private String errorMessage;
    private Map<String,String> fieldErrors;
}
