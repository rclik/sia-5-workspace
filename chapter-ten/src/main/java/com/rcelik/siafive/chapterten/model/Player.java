package com.rcelik.siafive.chapterten.model;

import lombok.Data;

// it seems lombok overrides equals method by default
@Data
public final class Player {
    private final String name;
    private final String surname;
}
