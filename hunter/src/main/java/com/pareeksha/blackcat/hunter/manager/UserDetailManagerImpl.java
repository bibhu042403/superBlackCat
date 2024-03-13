package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.UserDetailRepository;
import com.pareeksha.blackcat.hunter.entity.UserDetail;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailManagerImpl extends GenericManager<UserDetail, String> {
    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailManagerImpl(UserDetailRepository userDetailRepository){
        this.userDetailRepository = userDetailRepository;
            setCrudRepository(userDetailRepository);
    }

    public Optional<UserDetail> findByUserName(String userName){
        return userDetailRepository.findByUserName(userName);
    }


}
