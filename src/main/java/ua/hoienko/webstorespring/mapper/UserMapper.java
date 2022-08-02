package ua.hoienko.webstorespring.mapper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.hoienko.webstorespring.mapper.dto.UserDto;
import ua.hoienko.webstorespring.model.User;

import static ua.hoienko.webstorespring.model.Role.ROLE_USER;

@AllArgsConstructor
@Component
public class UserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User map(User user, UserDto userDto) {
        user.setBlocked(false);
        user.setEmail(userDto.getEmail());
        user.setRole(ROLE_USER);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return user;
    }
}