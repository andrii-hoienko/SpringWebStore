package ua.hoienko.webstorespring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hoienko.webstorespring.mapper.dto.ProductDto;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.Product;
import ua.hoienko.webstorespring.model.User;
import ua.hoienko.webstorespring.service.OrderService;
import ua.hoienko.webstorespring.service.ProductService;
import ua.hoienko.webstorespring.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserService userService;
    private OrderService orderService;
    private ProductService productService;

    @GetMapping
    public String getAdminPanelPage() {
        return "admin-panel";
    }

    @GetMapping("/products")
    public String getProductPage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        return "admin-panel-products";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin-panel-users";
    }

    @GetMapping("/orders")
    public String getOrdersPage(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin-panel-orders";
    }

    @GetMapping("/products/change")
    public String getProductChangePage(@RequestParam(value = "productId") long productId,
                                       Model model) {
        model.addAttribute("productId", productId);
        return "admin-panel-products-change";
    }

    @PostMapping("/users")
    public String blockUser(@RequestParam(value = "userIdToChangeStatus") long userId) {
        User user = userService.getUserById(userId);
        userService.changeUserBlockStatus(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/orders")
    public String changeOrderStatus(@RequestParam(value = "status") String status,
                                    @RequestParam(value = "orderToBeChanged") long orderId) {
        orderService.changeStatus(status, orderId);
        return "redirect:/admin/orders";
    }

    @PostMapping("/products/change")
    public String changeProduct(ProductDto productDto) {
        productService.updateProduct(productDto);
        return "redirect:/admin/products";
    }
}
