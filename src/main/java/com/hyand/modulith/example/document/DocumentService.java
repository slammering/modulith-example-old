package com.hyand.modulith.example.document;

import com.hyand.modulith.example.common.Document;
import com.hyand.modulith.example.status.ProcessComplete;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final ApplicationEventPublisher events;
    private final DocumentProperties documentProperties;

    public void handle(UUID id, Document document) {
        log.info("Send document to url {}", documentProperties.url());
        events.publishEvent(new ProcessComplete(id));
    }
}
