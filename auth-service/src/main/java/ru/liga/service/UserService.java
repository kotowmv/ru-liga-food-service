package ru.liga.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.liga.dto.RegisterDTO;
import ru.liga.model.CustomUserDetails;
import java.util.Collections;

@Service
public class UserService {

    private final UserDetailsService userDetailsService;

    @Qualifier("defaultPasswordEncoder")
    private final PasswordEncoder defaultPasswordEncoder;

    public UserService(UserDetailsService userDetailsService, PasswordEncoder defaultPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
    }

    public ResponseEntity<String> createUser(RegisterDTO dto) {
        CustomUserDetails customUserDetails = new CustomUserDetails(
                dto.getUsername(), defaultPasswordEncoder.encode(dto.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        try {
            if (userDetailsService.loadUserByUsername(dto.getUsername()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
            }
        } catch (UsernameNotFoundException e) {
            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
            return ResponseEntity.ok("Пользователь успешно создан");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
    }
}
