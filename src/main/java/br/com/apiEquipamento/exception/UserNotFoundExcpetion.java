package br.com.apiEquipamento.exception;

public class UserNotFoundExcpetion extends RuntimeException{
    public UserNotFoundExcpetion(String email) {
        super("Usuario não encontrado");
    }

    public UserNotFoundExcpetion(Long id) {
        super("Usuario não encontrado");
    }
}
