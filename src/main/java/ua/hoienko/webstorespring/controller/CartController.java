package ua.hoienko.webstorespring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hoienko.webstorespring.model.Cart;
import ua.hoienko.webstorespring.service.OrderService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private ObjectFactory<HttpSession> httpSessionFactory;
    private OrderService orderService;

    @GetMapping
    public String getCartPage() {

        return "cart";
    }

    public Cart getCart() {
        HttpSession session = httpSessionFactory.getObject();
        return (Cart) session.getAttribute("cart");
    }

    @PostMapping("/order")
    public String createOrder(Model model) {
        Cart cart = getCart();
        orderService.createOrder(cart);
        cart.clear();
        model.addAttribute("isOrderDone", true);
        return "cart";
    }

    @PostMapping("/delete")
    public String deleteProductFromCart(@RequestParam(value = "productToDeleteId") long productId) {
        HttpSession session = httpSessionFactory.getObject();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteByProductId(productId);
        return "redirect:/cart";
    }
}
