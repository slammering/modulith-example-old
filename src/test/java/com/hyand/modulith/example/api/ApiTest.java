package com.hyand.modulith.example.api;

import com.hyand.modulith.example.common.Input;
import com.hyand.modulith.example.common.MasterData;
import com.hyand.modulith.example.common.enums.InputType;
import com.hyand.modulith.example.status.ProcessComplete;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.UUID;

@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@RequiredArgsConstructor
public class ApiTest {

    private final Controller controller;

    @Test
    public void handle(Scenario scenario) {
        var id = UUID.randomUUID();
        var input = new Input(id, InputType.MASTERDATA, new MasterData("Yeah!"), null);
        scenario.stimulate(() -> controller.receive(input))
                .andWaitForEventOfType(ProcessComplete.class)
                .matchingMappedValue(ProcessComplete::id, id)
                .toArrive();
    }
}
