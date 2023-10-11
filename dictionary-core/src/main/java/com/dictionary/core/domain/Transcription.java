package com.dictionary.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "Transcription")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transcription {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(name = "value")
    private String value;

    @ManyToOne(targetEntity = Card.class)
    @JoinColumn(name = "cardId")
    @JsonIgnore
    private Card card;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "variant")
    private TranscriptionVariant variant;

    @Getter
    @Setter
    private transient MediaFile file;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fileid")
    private FileProperty fileProperty;

    public String getFileWrapperId() {
        return Objects.nonNull(fileProperty) ? fileProperty.getId() : null;
    }
}
