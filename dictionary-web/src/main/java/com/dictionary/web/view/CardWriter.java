package com.dictionary.web.view;

import com.dictionary.web.domain.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

import java.util.*;

public class CardWriter extends VerticalLayout {
    @Getter
    private final VerticalLayout layout = new VerticalLayout();

    @Getter
    private final CheckboxGroupView checkboxGroupView = new CheckboxGroupView(Set.of(
            ElementType.EXPLANATION, ElementType.IMAGE,
            ElementType.EXAMPLES, ElementType.AUDIO
    ));

    public CardWriter() {


    }

//
//    public static CardWriterBuilder builder() {
//        return new CardWriterBuilder();
//    }

//    public static class CardWriterBuilder {
//        private final CardWriter cardWriter = new CardWriter();
//
//        public CardWriterBuilder width(String width) {
//            cardWriter.layout.setWidth(width);
//            return this;
//        }
//
//        public CardWriterBuilder expression(String value) {
//            cardWriter.expression.setValue(value);
//            return this;
//        }
//
//        public CardWriterBuilder explanation(String value) {
//            cardWriter.explanation.setValue(value);
//            return this;
//        }
//
//        public CardWriterBuilder examples(List<String> examples) {
//            cardWriter.exampleList.setExamples(examples);
//            return this;
//        }
//
//        public CardWriterBuilder audio(List<String> examples) {
//            //cardWriter.audioList
//            return this;
//        }
//
//        public CardWriter build() {
//            return cardWriter;
//        }
//
//    }

}
