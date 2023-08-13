package com.pareeksha.blackcat.hunter.dao;

import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdmitCardRepository extends CrudRepository<AdmitCard, String> {

    Page<AdmitCard> findAll(Pageable pageable);

    Optional<AdmitCard> findByExamId(String examId);
}
