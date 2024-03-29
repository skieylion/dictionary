package com.dictionary.web.service.mapper;

import com.dictionary.core.domain.Slot;
import com.dictionary.core.domain.SlotStat;
import com.dictionary.web.domain.dto.SlotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlotMapper {

    default SlotDto convert(Slot slot, SlotStat slotStat) {
        SlotDto slotDto = new SlotDto();
        slotDto.setId(slot.getId());
        slotDto.setName(slot.getName());
        slotDto.setDescription(slot.getDescription());
        slotDto.setFileId(slot.getFileWrapperId());
        slotDto.setSlotStat(slotStat);
        return slotDto;
    }

    default List<SlotDto> convert(List<Slot> slotList, List<SlotStat> slotStatList) {
        List<SlotDto> slotDtoList = new ArrayList<>();
        if (Objects.nonNull(slotList)) {
            for (Slot slot : slotList) {
                for (SlotStat slotStat : slotStatList) {
                    if (slotStat.getSlotId().equals(slot.getId())) {
                        slotDtoList.add(convert(slot, slotStat));
                        break;
                    }
                }
            }
        }

        return slotDtoList;
    }
}
