package com.manu.jobapplicationtracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {
   public Error(String errorMessage){
        this.errorMessage = errorMessage;
    }
    String errorMessage;
}
