package com.pareeksha.blackcat.hunter.dao;

import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ResultDetailsRepository extends CrudRepository<ResultDetails, String> {
    Page<ResultDetails> findAll(Pageable pageable);
}
