package com.pareeksha.blackcat.hunter.manager;

import com.pareeksha.blackcat.hunter.dao.ResultDetailsRepository;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.hunter.sql.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResultDetailsManagerImpl extends GenericManager<ResultDetails, String> {

    private final ResultDetailsRepository resultDetailsRepository;

    @Autowired
    public ResultDetailsManagerImpl(ResultDetailsRepository resultDetailsRepository){
        this.resultDetailsRepository = resultDetailsRepository;
        setCrudRepository(resultDetailsRepository);
    }

    public Optional<List<ResultDetails>> getTopTenResult(int Limit){
        Page<ResultDetails> page = resultDetailsRepository.findAll(PageRequest.of(0,Limit,
                Sort.by(Sort.Order.desc("declaredDate"))));
        return Optional.of(page.getContent());
    }

    public Optional<ResultDetails> findResultById(String examId){
        return resultDetailsRepository.findById(examId);
    }
}
