package com.dictionary.application.service.command;

import com.dictionary.application.domain.*;
import com.dictionary.application.domain.dto.CardDTO;
import com.dictionary.application.domain.dto.CardWriterDto;
import com.dictionary.application.domain.dto.TranscriptionDTO;
import com.dictionary.application.repository.*;
import com.dictionary.application.service.FilePropertyService;
import com.dictionary.application.service.mapper.CardMapper;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardService {

    private final PartOfSpeechRepository partOfSpeechRepository;
    private final CardRepository cardRepository;
    private final SlotRepository slotRepository;
    private final CardAndSlotRepository cardAndSlotRepository;
    private final CardRememberRepository cardRememberRepository;
    private final TranscriptionRepository transcriptionRepository;
    private final ExampleRepository exampleRepository;
    private final CardMapper cardMapper;
    private final FilePropertyService filePropertyService;

    private Set<Transcription> createNewTranscriptions(Card card, List<TranscriptionDTO> transcriptionDTOList) {
        return Optional.ofNullable(transcriptionDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map(tr -> Transcription.builder()
                        .card(card)
                        .fileProperty(Optional.ofNullable(tr.getFileId()).stream()
                                .map(filePropertyService::findById)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .findFirst()
                                .orElse(null))
                        .value(tr.getValue())
                        .variant(tr.getVariant())
                        .build())
                .collect(Collectors.toSet());
    }

    @Transactional
    public void insert(CardDTO cardDTO, Collection<Slot> slots) {
        Card card = Card.builder()
                .expression(cardDTO.getExpression())
                .definition(cardDTO.getExplanation())
                .translate(cardDTO.getTranslation())
                .fileProperty(Optional.ofNullable(cardDTO.getPictureFile())
                        .map(filePropertyService::saveMediaFile)
                        .orElse(null))
                .examples(new HashSet<>())
                .transcriptions(new HashSet<>())
                .build();
        cardRepository.save(card);
        card.setExamples(cardDTO.getExamples().stream()
                .map(example -> Example.builder()
                        .card(card)
                        .text(example)
                        .build())
                .collect(Collectors.toSet()));
        card.setTranscriptions(cardDTO.getTranscriptions().stream()
                .map(t -> Transcription.builder()
                        .card(card)
                        .fileProperty(Optional.ofNullable(t.getFile()).stream()
                                .map(filePropertyService::saveMediaFile)
                                .findFirst()
                                .orElse(null))
                        .variant(t.getVariant())
                        .value(t.getValue())
                        .build())
                .collect(Collectors.toSet()));
        cardRepository.save(card);
        cardAndSlotRepository.saveAll(slots.stream()
                .map(slot -> CardAndSlot.builder()
                        .card(card)
                        .slot(slot)
                        .build())
                .collect(Collectors.toSet()));
    }

    @Transactional
    public void setExamples(long cardId, List<String> examples) {
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);
        Optional.ofNullable(examples)
                .orElse(new ArrayList<>()).stream()
                .map(text -> Example.builder().text(text).card(card).build())
                .forEach(exampleRepository::save);
    }

    @Transactional
    public void update(CardDTO cardDTO, Collection<Slot> slots) {
        Card card = cardRepository.findById(cardDTO.getId()).orElseThrow(EntityNotFoundException::new);
        long cardId = card.getId();
        exampleRepository.deleteByCardId(cardId);
        transcriptionRepository.deleteByCardId(cardId);
        cardAndSlotRepository.removeByCardId(cardId);
        Optional.ofNullable(card.getFileProperty()).stream()
                .peek(fileProperty -> {
                    card.setFileProperty(null);
                    cardRepository.save(card);
                    cardRepository.flush();
                }).forEach(filePropertyService::deleteByFileProperty);

        card.setExpression(cardDTO.getExpression());
        card.setDefinition(cardDTO.getExplanation());
        Optional.ofNullable(cardDTO.getPictureFile()).stream()
                .map(filePropertyService::saveMediaFile)
                .forEach(card::setFileProperty);
        card.setExamples(cardDTO.getExamples().stream()
                .map(value -> Example.builder().text(value).card(card).build())
                .collect(Collectors.toSet()));
        card.setTranscriptions(cardDTO.getTranscriptions().stream()
                .map(transcriptionDTO -> Transcription.builder()
                        .value(transcriptionDTO.getValue())
                        .card(card)
                        .variant(transcriptionDTO.getVariant())
                        .fileProperty(Optional.ofNullable(transcriptionDTO.getFile()).stream()
                                .map(filePropertyService::saveMediaFile)
                                .findFirst().orElse(null))
                        .build()
                ).peek(transcriptionRepository::save)
                .collect(Collectors.toSet()));
        card.setSlots(new HashSet<>(slots));
        cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    public Card findCardById(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);

        return card;
    }

    @Transactional
    public void repeatCard(Long cardId) {
//        cardRepository.findById(cardId).ifPresent(card -> {
//            CardRemember cardRemember = new CardRemember();
//            cardRemember.setEventDate(new Date());
//            cardRemember.setCard(card);
//            cardEventRepository.save(cardRemember);
//        });
    }

    @Transactional
    public void deleteCard(Long cardId) {
        var cardOptional = cardRepository.findById(cardId);
        cardOptional.stream()
                .map(Card::getTranscriptions)
                .flatMap(Collection::stream)
                .map(Transcription::getFileWrapperId)
                .filter(Objects::nonNull)
                .forEach(filePropertyService::deleteById);
        cardOptional.stream()
                .peek(card -> cardAndSlotRepository.removeByCardId(card.getId()))
                .forEach(cardRepository::delete);
    }

    @Transactional
    public void deleteByCardId(long cardId) {
        exampleRepository.deleteByCardId(cardId);
        Card card = cardRepository.findById(cardId).get();
        Example example = new Example();
        example.setText(String.valueOf(new Random().nextInt()));
        example.setCard(card);
        exampleRepository.save(example);
    }
}
