package com.manu.jobapplicationtracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisValueEntity {
    private String id;
    private String name;
    private String email;
}
