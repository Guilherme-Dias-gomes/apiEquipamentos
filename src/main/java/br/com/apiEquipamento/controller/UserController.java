package br.com.apiEquipamento.controller;

import br.com.apiEquipamento.dto.UserDetailsDTO;
import jakarta.validation.Valid;
import br.com.apiEquipamento.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.apiEquipamento.services.UserServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices services;

    @GetMapping("/{name}")
    public UserDetailsDTO listarByEmail(@PathVariable String email){
        return UserDetailsDTO.fromUser(services.getUserByEmail(email));
    }

    @GetMapping
    public List<UserDetailsDTO> listarTodos(){
        return services.getAllUser().stream()
                .map(UserDetailsDTO::fromUser)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<User> CriarUsuario(@RequestBody User user){
        User novoUser = services.PostUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
    }


}
