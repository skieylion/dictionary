package com.dictionary.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    public int hashCode() {
        return Objects.hash(id, text, cardId);
    }
}
