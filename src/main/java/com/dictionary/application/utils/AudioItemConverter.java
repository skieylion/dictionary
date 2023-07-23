package com.dictionary.application.utils;

import com.dictionary.application.domain.AudioItem;
import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.dto.TranscriptionDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AudioItemConverter {
    public static List<TranscriptionDTO> transcriptionDtoList(List<AudioItem> audioItemList) {
        return audioItemList.stream()
                .map(audioItem -> {
                    TranscriptionDTO transcriptionDto = new TranscriptionDTO();
                    transcriptionDto.setVariant(audioItem.getSelect().getValue());
                    transcriptionDto.setValue(audioItem.getTextField().getValue());
                    MediaFile mediaFile = audioItem.getFile().orElseThrow(EntityNotFoundException::new);
                    mediaFile.setName(UUID.randomUUID().toString());
                    transcriptionDto.setFileName(mediaFile.getFullName());
                    return transcriptionDto;
                })
                .collect(Collectors.toList());
    }

}
