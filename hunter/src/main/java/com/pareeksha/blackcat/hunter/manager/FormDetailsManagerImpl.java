package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.FormDetailsRepository;
import com.pareeksha.blackcat.hunter.entity.FormDetails;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FormDetailsManagerImpl extends GenericManager<FormDetails, String> {

    private final FormDetailsRepository formDetailsRepository;

    @Autowired
    public FormDetailsManagerImpl(FormDetailsRepository formDetailsRepository){
        this.formDetailsRepository = formDetailsRepository;
        setCrudRepository(formDetailsRepository);
    }

    public List<FormDetails> findAllFormByExamBoard(String examBoard){
        return formDetailsRepository.findByExamBoard(examBoard);
    }

}
