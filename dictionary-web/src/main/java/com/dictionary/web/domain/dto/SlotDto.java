package com.dictionary.web.domain.dto;

import com.dictionary.web.domain.SlotStat;
import lombok.Data;

@Data
public class SlotDto {
    private long id;
    private String name;
    private String description;
    private String fileId;
    private SlotStat slotStat;
}
