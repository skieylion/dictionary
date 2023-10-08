package com.dictionary.core.domain;

import com.dictionary.core.domain.Slot;
import lombok.*;

import javax.persistence.*;

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
