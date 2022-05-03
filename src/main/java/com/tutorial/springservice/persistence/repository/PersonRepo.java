package com.tutorial.springservice.persistence.repository;

import com.tutorial.springservice.persistence.mapper.PersonMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonMapper, Long> {
}
