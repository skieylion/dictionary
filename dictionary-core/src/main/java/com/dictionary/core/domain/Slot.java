package com.dictionary.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
