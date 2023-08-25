package com.dictionary.application.domain.dto;

import com.dictionary.application.domain.PictureFile;
import com.dictionary.application.view.box.CardBox;
import lombok.Data;

import java.util.List;

@Data
public class CardWriterDto {
    private String expression;
    private long partOfSpeechId;
    private String definition;
    private String translate;
    private List<TranscriptionDTO> transcriptionList;
    private List<String> exampleList;
    private List<Long> slotIds;
    private Long cardId;
    private PictureFile pictureFile;

    public static CardWriterDtoBuilder builder() {
        return new CardWriterDtoBuilder();
    }

    public static class CardWriterDtoBuilder {
        private CardWriterDto cardWriterDto = new CardWriterDto();
        private CardBox cardBox;

        public CardWriterDtoBuilder cardId(Long cardId) {
            cardWriterDto.setCardId(cardId);
            return this;
        }

        public CardWriterDtoBuilder cardWriter(CardBox cardBox) {
            this.cardBox = cardBox;
            return this;
        }

        private void addSlotIds() {
//            cardWriterDto.setSlotIds(cardBox.getSlotBox().getSelectedItems().stream()
//                    .map(Pair::getFirst)
//                    .collect(Collectors.toList()));
        }

        private void addPicture() {
//            if (cardBox.getSelectedItems().contains(ElementType.IMAGE)) {
//                cardWriterDto.setPictureFile(cardBox.getCardWriter().getPictureLoader().getPicture());
//            }
        }

        private void addDefinition() {
//            if (cardBox.getSelectedItems().contains(ElementType.EXPLANATION)) {
//                cardWriterDto.setDefinition(cardBox.getCardWriter().getExplanation().getValue());
//            }
        }

        private void addExamples() {
//            if (cardBox.getSelectedItems().contains(ElementType.EXAMPLES)) {
//                cardWriterDto.setExampleList(cardBox.getCardWriter().getExampleList().getExamples().stream()
//                        .map(item -> item.getTextField().getValue())
//                        .collect(Collectors.toList()));
//            }
        }

        private void addTranscriptions() {
//            if (cardBox.getSelectedItems().contains(ElementType.AUDIO)) {
//                cardWriterDto.setTranscriptionList(AudioItemConverter
//                        .transcriptionDtoList(cardBox.getAudioItemList()));
//            }
        }

        public CardWriterDto build() {
//            cardWriterDto.setExpression(cardBox.getCardWriter().getExpression().getValue());
//            addSlotIds();
//            addPicture();
//            addDefinition();
//            addExamples();
//            addTranscriptions();
            return cardWriterDto;
        }
    }
}
