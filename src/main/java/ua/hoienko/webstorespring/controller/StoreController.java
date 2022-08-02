package ua.hoienko.webstorespring.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hoienko.webstorespring.model.Cart;
import ua.hoienko.webstorespring.model.Product;
import ua.hoienko.webstorespring.service.ProductService;
import ua.hoienko.webstorespring.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class StoreController {

    private ProductService productService;
    ObjectFactory<HttpSession> httpSessionFactory;

    @GetMapping("/store")
    public String getStorePage(@RequestParam(value = "filter", required = false) String filterParameter,
                               Model model) {
        HttpSession session = httpSessionFactory.getObject();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            session.setAttribute("cart", new Cart());
        }
        List<Product> allProducts = productService.getAllProducts();
        if (filterParameter != null) {
//            allProducts.stream().filter()
        }
        model.addAttribute("products", allProducts);
        return "main";
    }

    @GetMapping("/store/buy")
    public String addProductToCart(@RequestParam(value = "amount") int amount,
                                   @RequestParam(value = "productId") long productId) {
        HttpSession session = httpSessionFactory.getObject();
        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.getProductById(productId);
        if (cart.isPresent(product)) {
            int currentAmount = cart.getProducts().get(product);
            int newAmount = currentAmount + amount;
            cart.addProduct(newAmount, product);
        } else {
            cart.addProduct(amount, product);
        }
        return "redirect:/store";
    }
}
