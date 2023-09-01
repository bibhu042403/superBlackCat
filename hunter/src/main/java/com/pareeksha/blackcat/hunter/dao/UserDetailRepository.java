package com.pareeksha.blackcat.hunter.dao;

import com.pareeksha.blackcat.hunter.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDetailRepository extends CrudRepository<UserDetail, String> {

    Optional<UserDetail> findByUserName(String userName);
}
