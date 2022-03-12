package com.tutorial.springservice.person.service.response;

import com.tutorial.springservice.person.entity.Person;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PeopleListResponse {
    List<Person> people;
}
