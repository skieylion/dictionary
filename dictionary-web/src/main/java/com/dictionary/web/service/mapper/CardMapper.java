package com.dictionary.web.service.mapper;

import com.dictionary.web.domain.Card;
import com.dictionary.web.domain.FileProperty;
import com.dictionary.web.domain.dto.CardDTO;
import org.mapstruct.Mapper;

import java.util.HashSet;

@Mapper(componentModel = "spring")
public interface CardMapper {
    default Card fromCardDTO(CardDTO cardDTO) {
        return Card.builder()
                .expression(cardDTO.getExpression())
                .definition(cardDTO.getExplanation())
                .translate(cardDTO.getTranslation())
                .fileProperty(cardDTO.getPictureFile() != null ? FileProperty
                        .fromMediaFile(cardDTO.getPictureFile()) : null)
                .examples(new HashSet<>())
                .transcriptions(new HashSet<>())
                .build();
    }


}
