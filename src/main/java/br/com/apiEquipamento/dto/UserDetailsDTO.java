package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.User;

public record UserDetailsDTO(
        String nome,
        String setor
) {
    public static UserDetailsDTO fromUser(User user){
        return new UserDetailsDTO(
                user.getNome(),
                user.getSetor()
        );
    }
}
