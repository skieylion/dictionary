package com.dictionary.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Example")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = Card.class)
    @JoinColumn(name = "cardId")
    @JsonIgnore
    private Card card;

    @Column(name = "cardId", insertable = false, updatable = false)
    private Long cardId;

    @Override
    public boolean equals(Object obj) {
        Example example = (Example) obj;
        if (!Objects.nonNull(example.getCard())) {
            return false;
        }
        if (!Objects.nonNull(card)) {
            return false;
        }
        String text = example.getText();
        long cardId = example.getCard().getId();
        return Objects.equals(text, this.text)
                && Objects.nonNull(text)
                && cardId == card.getId()
                && cardId != 0
                && card.getId() != 0;
    }
}
