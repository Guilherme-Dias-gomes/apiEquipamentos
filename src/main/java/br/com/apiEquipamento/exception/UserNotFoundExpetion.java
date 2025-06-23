package br.com.apiEquipamento.exception;

public class UserNotFoundExpetion extends RuntimeException{
    public UserNotFoundExpetion(String email) {
        super("Usuario não encontrado");
    }

    public UserNotFoundExpetion(Long id) {
        super("Usuario não encontrado");
    }
}
