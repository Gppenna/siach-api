package com.siach.api.enumeration;

public enum StatusInternoEnum {
    RASCUNHO("R", "Rascunho"),
    FINALIZADO("F", "Finalizado"),
    EXCEDENTE("E", "Excedente"),
    EXCEDENTE_ATIVO("X", "Excedente Ativo"),
    ATIVO("A", "Ativo"),
    INATIVO("I", "Inativo");

    private final String key;
    private final String value;

    StatusInternoEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
