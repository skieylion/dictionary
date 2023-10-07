package com.dictionary.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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
