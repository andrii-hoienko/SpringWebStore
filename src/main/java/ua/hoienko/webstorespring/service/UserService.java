package ua.hoienko.webstorespring.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.hoienko.webstorespring.mapper.dto.UserDto;
import ua.hoienko.webstorespring.mapper.UserMapper;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.Role;
import ua.hoienko.webstorespring.model.User;
import ua.hoienko.webstorespring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getCurrentUser() {
        return (User) getContext().getAuthentication().getPrincipal();
    }

    public boolean isUser() {
        return getCurrentUser().getRole().equals(Role.ROLE_USER);
    }

    public boolean isAdmin() {
        return getCurrentUser().getRole().equals(Role.ROLE_ADMIN);
    }

    public boolean isUserAlreadyExistsByEmail(String email) {
        try {
            findUserByEmail(email);
        } catch (Exception ignored) {
            return true;
        }
        return false;
    }

    public void createUser(UserDto userDto) {
        User user = new User();
        userMapper.map(user, userDto);
        userRepository.save(user);
    }

    public void changeUserBlockStatus(User user) {
        user.setBlocked(!user.isBlocked());
        userRepository.save(user);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public List<Order> findAllOrdersByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow().getOrderList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    public void fillTableWithData() {
        User admin = new User();
        admin.setRole(Role.ROLE_ADMIN);
        admin.setLastName("admin");
        admin.setFirstName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setBlocked(false);
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));

        userRepository.save(admin);

        User user = new User();
        admin.setRole(Role.ROLE_USER);
        admin.setLastName("user");
        admin.setFirstName("user");
        admin.setEmail("user@gmail.com");
        admin.setBlocked(false);
        admin.setPassword(bCryptPasswordEncoder.encode("user"));

        userRepository.save(user);
    }
}
