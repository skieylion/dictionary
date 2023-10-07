package com.dictionary.web.domain.dto;

import com.dictionary.web.domain.MediaFile;
import lombok.Data;

@Data
public class SlotCreatorDto {
    private String name;
    private MediaFile file;
}
