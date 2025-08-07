package br.com.apiEquipamento.controller;

import br.com.apiEquipamento.dto.AlterarSenhaDTO;
import br.com.apiEquipamento.dto.AuthenticationDTO;
import br.com.apiEquipamento.dto.LoginResponseDto;
import br.com.apiEquipamento.exception.UserNotFoundExcpetion;
import br.com.apiEquipamento.model.User;
import br.com.apiEquipamento.repository.UserRepository;
import br.com.apiEquipamento.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        if(this.repository.findByEmail(user.getEmail()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getSenha());
        User newUser = new User(user.getEmail(), encryptedPassword, user.getNome(), user.getSetor(), user.getRole());
        System.out.println("Authorities: " + user.getAuthorities());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/senha")
    public ResponseEntity<?> alterarSenha(@RequestBody AlterarSenhaDTO dto, @PathVariable Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundExcpetion("Usuário não encontrado"));

        user.setSenha(passwordEncoder.encode(dto.novaSenha()));
        repository.save(user);

        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
