package com.dictionary.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
