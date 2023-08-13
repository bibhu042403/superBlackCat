package com.pareeksha.blackcat.hunter.dao;

import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationFormDetailsRepository extends CrudRepository<ApplicationFormDetails, String> {

    Page<ApplicationFormDetails> findAll(Pageable pageable);

    Optional<ApplicationFormDetails> findByExamId(String examId);
}
