package com.dictionary.application.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "card_remember")
public class CardRemember implements Comparable<CardRemember> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_time")
    private LocalDateTime eventDate;

    @Column(name = "counter")
    private int counter;

    @OneToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;

    @Override
    public int compareTo(CardRemember o) {
        return o.getEventDate().compareTo(getEventDate());
    }
}
