package br.com.apiEquipamento.controller;

import br.com.apiEquipamento.dto.UserDetailsDTO;
import br.com.apiEquipamento.dto.UserUpdateDTO;
import jakarta.validation.Valid;
import br.com.apiEquipamento.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import br.com.apiEquipamento.services.UserServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices services;

    @GetMapping("/{id}")
    public UserDetailsDTO listarById(@PathVariable Long id){
        return UserDetailsDTO.fromUser(services.getUserById(id));
    }

    @GetMapping
    public List<UserDetailsDTO> listarTodos(){
        return services.getAllUser().stream()
                .map(UserDetailsDTO::fromUser)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public void DeletarUsuario(@PathVariable Long id){
        services.DeleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> AlterarUser(@RequestBody @Valid UserUpdateDTO userDto, @PathVariable Long id ){
        User userAlterado = services.AtualizarUser(userDto, id);
        UserUpdateDTO response = new UserUpdateDTO(
                userAlterado.getNome(),
                userAlterado.getSetor()
        );
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
