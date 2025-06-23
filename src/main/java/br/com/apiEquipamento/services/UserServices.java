package br.com.apiEquipamento.services;

import br.com.apiEquipamento.exception.UserNotFoundExpetion;
import br.com.apiEquipamento.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.apiEquipamento.repository.UserRepository;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserByEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundExpetion(email)
        );
    }

    public User PostUser(User user){
        return repository.save(user);
    }
    public User AtualizarUser (User user, Long id){
        User userExistente = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundExpetion("Usuario não enocntrado com o Id " + id));

        return repository.save(userExistente);
    }

    public void DeleteUser(Long id){
        repository.findById(id).orElseThrow(
                () -> new UserNotFoundExpetion(id)
        );
        repository.deleteById(id);
    }
}
