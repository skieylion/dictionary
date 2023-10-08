package com.dictionary.core.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "filetable")
@NoArgsConstructor
public class FileProperty {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "fileid")
    private UUID id;

    @Getter
    @Column(name = "format")
    private String format;

    @Getter
    @Column(name = "external_id")
    private String externalId;

    @Getter
    @Setter
    private transient MediaFile mediaFile;

    private FileProperty(MediaFile mediaFile) {
        this.id = mediaFile.getUuid();
        this.format = mediaFile.getMediaType().getExtension();
        this.externalId = mediaFile.getUuidAndExt();
        this.mediaFile = mediaFile;
    }

    public String getId() {
        return id.toString();
    }

    public static FileProperty fromMediaFile(MediaFile file) {
        return new FileProperty(file);
    }
}
