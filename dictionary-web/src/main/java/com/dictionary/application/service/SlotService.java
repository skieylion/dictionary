package com.dictionary.application.service;

import com.dictionary.application.domain.Card;
import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.domain.Slot;
import com.dictionary.application.domain.SlotStat;
import com.dictionary.application.repository.CardAndSlotRepository;
import com.dictionary.application.repository.SlotRepository;
import com.dictionary.application.repository.batis.SlotStatRepository;
import com.dictionary.application.service.command.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SlotService {
    private final SlotRepository slotRepository;
    private final CardService cardService;
    private final CardAndSlotRepository cardAndSlotRepository;
    private final SlotStatRepository slotStatRepository;
    private final FilePropertyService filePropertyService;
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

    @Transactional
    public void save(Slot slot) {
        slotRepository.save(slot);
    }

    @Transactional
    public void insertSlot(String name, MediaFile file) {
        var slot = new Slot();
        Optional.ofNullable(file).stream()
                .map(filePropertyService::saveMediaFile)
                .forEach(slot::setFileProperty);
        slot.setName(name);
        slotRepository.save(slot);
    }

    @Transactional
    public void delete(long slotId) {
        Slot slot = findById(slotId);
        Set<Card> cards = slot.getCards();
        if (cards != null) {
            for (Card card : cards) {
                Set<Slot> slots = card.getSlots();
                if (slots != null && slots.size() == 1) {
                    cardService.deleteCard(card.getId());
                }
            }
        }
        cardAndSlotRepository.removeBySlotId(slot.getId());
        slotRepository.delete(slot);
        entityManager.flush();
        Optional.ofNullable(slot.getFileWrapperId())
                .ifPresent(filePropertyService::deleteById);
    }

    @Transactional(readOnly = true)
    public Slot findById(long slotId) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(EntityNotFoundException::new);
        return slot;
    }

    public Integer getCountOverdueCards() {
        return slotStatRepository.getSlotStat().stream()
                .map(SlotStat::getOverdueCount)
                .reduce(Integer::sum)
                .orElse(0);
    }

}
