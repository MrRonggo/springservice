package com.tutorial.springservice.core.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateRequest {
    private String firstName;
    private String lastName;
    private int dateOfBirth;
}
