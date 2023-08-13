package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.SystemPropertyRepository;
import com.pareeksha.blackcat.hunter.entity.SystemProperty;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("systemPropertyManager")
@Transactional
public class SystemPropertyManagerImpl extends GenericManager<SystemProperty, String> {
    SystemPropertyRepository systemPropertyRepository;

    @Autowired
    public SystemPropertyManagerImpl(SystemPropertyRepository systemPropertyRepository){
        this.systemPropertyRepository = systemPropertyRepository;
        setCrudRepository(systemPropertyRepository);
    }
}
