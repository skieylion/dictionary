package com.dictionary.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "slot")
public class Slot {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @Column(name = "description")
    private String description;

    @Getter
    @ManyToMany(mappedBy = "slots", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Card> cards;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fileid")
    private FileProperty fileProperty;

    public String getFileWrapperId() {
        return fileProperty != null ? fileProperty.getId() : null;
    }
}
