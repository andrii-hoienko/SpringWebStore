package ua.hoienko.webstorespring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.hoienko.webstorespring.mapper.dto.UserDto;
import ua.hoienko.webstorespring.service.UserService;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String register(UserDto userDto) {
        if (userService.isUserAlreadyExistsByEmail(userDto.getEmail())) {
            userService.createUser(userDto);
        }
        return "redirect:/login";
    }
}
