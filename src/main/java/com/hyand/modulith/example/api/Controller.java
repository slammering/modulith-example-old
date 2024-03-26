package com.hyand.modulith.example.api;

import com.hyand.modulith.example.common.Input;
import com.hyand.modulith.example.document.DocumentService;
import com.hyand.modulith.example.masterdata.MasterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final MasterDataService masterDataService;
    private final DocumentService documentService;

    @PostMapping("/receive")
    @Transactional
    public ResponseEntity<String> receive(Input input) {
        switch (input.inputType()) {
            case MASTERDATA -> masterDataService.handle(input.id(), input.masterData());
            case DOCUMENT -> documentService.handle(input.id(), input.document());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
