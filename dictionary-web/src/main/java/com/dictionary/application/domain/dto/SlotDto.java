package com.dictionary.application.domain.dto;

import com.dictionary.application.domain.SlotStat;
import lombok.Data;

@Data
public class SlotDto {
    private long id;
    private String name;
    private String description;
    private String fileId;
    private SlotStat slotStat;
}
