package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.ApplicationFormDetailsRepository;
import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ApplicationFormDetailsManagerImpl extends GenericManager<ApplicationFormDetails, String> {

    private final ApplicationFormDetailsRepository applicationFormDetailsRepository;

    @Autowired
    public ApplicationFormDetailsManagerImpl(ApplicationFormDetailsRepository applicationFormDetailsRepository){
        this.applicationFormDetailsRepository = applicationFormDetailsRepository;
        setCrudRepository(applicationFormDetailsRepository);
    }

    public Optional<List<ApplicationFormDetails>> getTopTenForm(int Limit){
        Page<ApplicationFormDetails> page = applicationFormDetailsRepository.findAll(PageRequest.of(0,Limit,
                Sort.by(Sort.Order.desc("startDate"))));
        return Optional.of(page.getContent());
    }

    public Optional<ApplicationFormDetails> findApplicationByExamId(String examId){
        return applicationFormDetailsRepository.findByExamId(examId);
    }

}
