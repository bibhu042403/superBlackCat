package com.pareeksha.blackcat.hunter.dao;

import com.pareeksha.blackcat.hunter.entity.FormDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormDetailsRepository extends CrudRepository<FormDetails, String> {
    List<FormDetails> findByExamBoard(String examBoard);
}
