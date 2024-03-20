package com.hyand.modulith.example.common;

import com.hyand.modulith.example.common.enums.InputType;

import java.util.UUID;

public record Input(UUID id, InputType inputType, MasterData masterData, Document document) {

}
