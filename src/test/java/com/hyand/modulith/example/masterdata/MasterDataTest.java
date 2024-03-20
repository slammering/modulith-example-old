package com.hyand.modulith.example.masterdata;

import com.hyand.modulith.example.common.MasterData;
import com.hyand.modulith.example.status.ProcessComplete;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.UUID;

@ApplicationModuleTest
@RequiredArgsConstructor
public class MasterDataTest {

    private final MasterDataService masterDataService;

    @Test
    public void handle(Scenario scenario) {
        UUID id = UUID.randomUUID();
        MasterData masterData = new MasterData("Yeah!");
        scenario.stimulate(() -> masterDataService.handle(id, masterData))
                .andWaitForEventOfType(ProcessComplete.class)
                .matchingMappedValue(ProcessComplete::id, id)
                .toArrive();
    }
}
