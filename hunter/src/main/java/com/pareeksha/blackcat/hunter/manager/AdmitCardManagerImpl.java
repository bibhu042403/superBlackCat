package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.AdmitCardRepository;
import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdmitCardManagerImpl extends GenericManager<AdmitCard, String> {
    private final AdmitCardRepository admitCardRepository;

    @Autowired
    public AdmitCardManagerImpl(AdmitCardRepository admitCardRepository){
        this.admitCardRepository = admitCardRepository;
        setCrudRepository(admitCardRepository);
    }

    public Optional<List<AdmitCard>> getTopTenAdmitCard(int Limit){
        Page<AdmitCard> page = admitCardRepository.findAll(PageRequest.of(0,Limit,
                Sort.by(Sort.Order.desc("declaredDate"))));
        return Optional.of(page.getContent());
    }

    public Optional<AdmitCard> findAdmitByExamId(String examId){
        return admitCardRepository.findByExamId(examId);
    }

}
