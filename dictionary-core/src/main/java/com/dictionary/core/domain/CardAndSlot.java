package com.dictionary.core.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cardandslot")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardAndSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Card.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cardId")
    private Card card;

    @ManyToOne(targetEntity = Slot.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "slotId")
    private Slot slot;
}
