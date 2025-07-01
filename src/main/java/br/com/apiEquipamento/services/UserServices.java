package br.com.apiEquipamento.services;

import br.com.apiEquipamento.dto.UserUpdateDTO;
import br.com.apiEquipamento.exception.UserNotFoundExcpetion;
import br.com.apiEquipamento.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.apiEquipamento.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new UserNotFoundExcpetion(id)
        );
    }

    public User PostUser(User user){
        return repository.save(user);
    }

    public User AtualizarUser (UserUpdateDTO userDto, Long id){
        User userExistente = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundExcpetion("Usuario não enocntrado com o Id " + id));

        userExistente.setNome(userDto.nome());
        userExistente.setSetor(userDto.setor());

        return repository.save(userExistente);
    }

    public void DeleteUser(Long id){
        repository.findById(id).orElseThrow(
                () -> new UserNotFoundExcpetion(id)
        );
        repository.deleteById(id);
    }
}
