package com.tutorial.springservice.person.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Person {
    private Long id;
    private String name;
    private LocalDate dayOfBirth;
    private String email;
}
