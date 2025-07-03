package br.com.apiEquipamento.model;

import br.com.apiEquipamento.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (repository.findByEmail("admin@empresa.com") == null) {
            User admin = new User(
                    "admin@empresa.com",
                    passwordEncoder.encode("admin123"),
                    "Administrador",
                    "TI",
                    UserRole.ADMIN
            );
            repository.save(admin);
        }
    }
}
