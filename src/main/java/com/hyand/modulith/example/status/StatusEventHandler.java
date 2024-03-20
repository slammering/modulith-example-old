package com.hyand.modulith.example.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatusEventHandler {

    @ApplicationModuleListener
    public void on(ProcessComplete processComplete) {
        log.info("Process complete {}", processComplete.id());
    }
}
