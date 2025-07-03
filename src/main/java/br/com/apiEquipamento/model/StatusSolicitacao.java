package br.com.apiEquipamento.model;

public enum StatusSolicitacao {
    NORMAL("Normal"),
    MEDIO("Mediow"),
    URGENTE("Urgente");

    private final String label;

    StatusSolicitacao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
