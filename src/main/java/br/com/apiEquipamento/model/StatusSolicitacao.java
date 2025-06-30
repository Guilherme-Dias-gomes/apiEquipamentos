package br.com.apiEquipamento.model;

public enum StatusSolicitacao {
    NORMAL("Normal"),
    MEDIANO("Mediano"),
    URGENTE("Urgente");

    private final String label;

    StatusSolicitacao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
