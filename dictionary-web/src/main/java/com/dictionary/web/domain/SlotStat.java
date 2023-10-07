package com.dictionary.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlotStat {
    private Long slotId;
    private int overdueCount;
    private int waitingCount;
    private int studiedCount;
    private int totalCount;
}
