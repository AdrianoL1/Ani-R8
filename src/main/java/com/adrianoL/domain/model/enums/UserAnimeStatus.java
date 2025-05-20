package com.adrianoL.domain.model.enums;

public enum UserAnimeStatus {
    WATCHING("WATCHING"),
    COMPLETED("COMPLETED"),
    OH("ON-HOLD"),
    DROPPED("DROPPED"),
    PTW("PLAN TO WATCH");

    public final String label;

    private UserAnimeStatus(String label){
        this.label = label;
    }
}
