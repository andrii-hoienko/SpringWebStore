package ua.hoienko.webstorespring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.User;
import ua.hoienko.webstorespring.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class UserProfileController {

    private UserService userService;

    @GetMapping
    public String getProfilePage(Model model) {
        User user = userService.getUserById(userService.getCurrentUser().getId());
        List<Order> orderList = user.getOrderList();
        model.addAttribute("orders", orderList);
        return "profile";
    }
}
