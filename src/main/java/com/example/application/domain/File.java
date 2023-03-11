package com.example.application.domain;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class File {
    private MemoryBuffer buffer;
    private String fileName;
    private String mimeType;
    private long size;
}
