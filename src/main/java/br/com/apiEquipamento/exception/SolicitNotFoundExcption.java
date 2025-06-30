package br.com.apiEquipamento.exception;

public class SolicitNotFoundExcption extends RuntimeException{
    public SolicitNotFoundExcption(Long id) { super("Solicitação não encontrada"); }
}
