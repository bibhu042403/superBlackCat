package com.pareeksha.blackcat.avenger.util;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.googlecode.jmapper.api.enums.MappingType;
import com.googlecode.jmapper.api.enums.NullPointerControl;
import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import org.springframework.stereotype.Component;

import static com.googlecode.jmapper.api.JMapperAPI.global;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;

@Component
public class JmapperUtil {

    //ENTITY to DTO
    private static final JMapperAPI admitCardDTOJmapperApi = new JMapperAPI()
            .add(mappedClass(AdmitCard.class)
                    .add(global()));

    private static final JMapperAPI resultDTOJmapperApi = new JMapperAPI()
            .add(mappedClass(ResultDetails.class)
                    .add(global()));



    private static final JMapper<AdmitCardDTO, AdmitCard> admitCardDTOMapper = new JMapper<>
            (AdmitCardDTO.class, AdmitCard.class, admitCardDTOJmapperApi);
    private static final JMapper<ResultDetailsDTO, ResultDetails> resultDTOMapper = new JMapper<>
            (ResultDetailsDTO.class, ResultDetails.class, resultDTOJmapperApi);



    public static AdmitCardDTO constructAdmitCardDTO(AdmitCard admitCard) {
        return admitCard == null ? null : admitCardDTOMapper.getDestination(admitCard);
    }

    public static ResultDetailsDTO constructResultDTO(ResultDetails resultDetails) {
        return resultDetails == null ? null : resultDTOMapper.getDestination(resultDetails);
    }



    //DTO to ENTITY
    private static final JMapperAPI admitCardJmapperApi = new JMapperAPI()
            .add(mappedClass(AdmitCardDTO.class)
                    .add(global()));

    private static final JMapperAPI resultJmapperApi = new JMapperAPI()
            .add(mappedClass(ResultDetailsDTO.class)
                    .add(global()));


    private static  final JMapper<AdmitCard, AdmitCardDTO> admitCardMapper = new JMapper<>
            (AdmitCard.class, AdmitCardDTO.class, admitCardJmapperApi);


    private static final JMapper<ResultDetails, ResultDetailsDTO> resultMapper = new JMapper<>
            (ResultDetails.class, ResultDetailsDTO.class, resultJmapperApi);

    public static AdmitCard constructAdmitCard(AdmitCardDTO admitCardDTO) {
        AdmitCard admitCard = new AdmitCard();
        return admitCardDTO == null ? null :
                admitCardMapper.getDestination(admitCard, admitCardDTO,
                        NullPointerControl.DESTINATION, MappingType.ALL_FIELDS, MappingType.ONLY_VALUED_FIELDS);
    }

    public static ResultDetails constructResult(ResultDetailsDTO resultDetailsDTO) {
        ResultDetails resultDetails = new ResultDetails();
        return resultDetailsDTO == null ? null :
                resultMapper.getDestination(resultDetails, resultDetailsDTO,
                        NullPointerControl.DESTINATION, MappingType.ALL_FIELDS, MappingType.ONLY_VALUED_FIELDS);
    }
}
