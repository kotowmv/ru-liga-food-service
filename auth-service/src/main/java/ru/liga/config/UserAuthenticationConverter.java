package ru.liga.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import ru.liga.dto.UserDTO;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationConverter implements AuthenticationConverter {

    private final ObjectMapper MAPPER;

    @Override
    public Authentication convert(HttpServletRequest request) {
        UserDTO userDto = null;
        try {
            userDto = MAPPER.readValue(request.getInputStream(), UserDTO.class);
        } catch (IOException e) {
            return null;
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(userDto.getLogin(), userDto.getPassword());
    }
}
