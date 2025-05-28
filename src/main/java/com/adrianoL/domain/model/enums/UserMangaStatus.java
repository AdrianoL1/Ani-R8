package com.adrianoL.domain.model.enums;

public enum UserMangaStatus {
    READING("READING"),
    COMPLETED("COMPLETED"),
    OH("ON-HOLD"),
    DROPPED("DROPPED"),
    PTR("PLAN TO READ");

    public final String label;

    private UserMangaStatus(String label){
        this.label = label;
    }
}
