package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.User;

public record UserDetailsDTO(
        Long id,
        String nome,
        String setor
) {
    public static UserDetailsDTO fromUser(User user){
        return new UserDetailsDTO(
                user.getId(),
                user.getNome(),
                user.getSetor()
        );
    }
}
