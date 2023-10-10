package com.dictionary.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "card")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "definition")
    private String definition;

    @Getter
    @Column(name = "translate")
    private String translate;

    @ManyToOne(targetEntity = PartOfSpeech.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "partOfSpeechId")
    private PartOfSpeech partOfSpeech;

    @Getter
    @Setter
    @Column(name = "expression")
    private String expression;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Example.class, mappedBy = "card", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Example> examples = new HashSet<>();

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Transcription.class, mappedBy = "card", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transcription> transcriptions = new HashSet<>();


    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Slot.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CardAndSlot",
            joinColumns = @JoinColumn(name = "cardId"),
            inverseJoinColumns = @JoinColumn(name = "slotId")
    )
    private Set<Slot> slots = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "state")
    private boolean state;

    @Getter
    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL)
    private CardRemember cardRemember;

    @Setter
    @Getter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photoid")
    private FileProperty fileProperty;

    public String getFileWrapperId() {
        return Objects.nonNull(fileProperty) ? fileProperty.getId() : null;
    }

    @Getter
    @Setter
    private transient PictureFile pictureFile;
}
