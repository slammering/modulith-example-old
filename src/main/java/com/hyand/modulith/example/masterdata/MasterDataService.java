package com.hyand.modulith.example.masterdata;

import com.hyand.modulith.example.common.MasterData;
import com.hyand.modulith.example.masterdata.entities.MasterDataEntity;
import com.hyand.modulith.example.masterdata.repository.MasterDataRepository;
import com.hyand.modulith.example.status.ProcessComplete;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterDataService {

    private final ApplicationEventPublisher events;
    private final MasterDataRepository masterDataRepository;

    @Transactional
    public void handle(UUID id, MasterData masterData) {
        masterDataRepository.save(map(id, masterData));
        events.publishEvent(new ProcessComplete(id));
    }

    private MasterDataEntity map(UUID id, MasterData masterData) {
        return MasterDataEntity.builder()
                .id(id)
                .key(masterData.key())
                .build();
    }
}
