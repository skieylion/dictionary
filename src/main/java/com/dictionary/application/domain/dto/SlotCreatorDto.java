package com.dictionary.application.domain.dto;

import com.dictionary.application.domain.MediaFile;
import lombok.Data;

@Data
public class SlotCreatorDto {
    private String name;
    private MediaFile file;
}
