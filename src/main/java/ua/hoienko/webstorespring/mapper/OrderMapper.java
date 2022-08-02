package ua.hoienko.webstorespring.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hoienko.webstorespring.model.Cart;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.OrderProduct;
import ua.hoienko.webstorespring.service.ProductService;
import ua.hoienko.webstorespring.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ua.hoienko.webstorespring.model.Status.REGISTRATIONS;

@AllArgsConstructor
@Component
public class OrderMapper {

    private final UserService userService;
    private final ProductService productService;

    public Order map(Order order, Cart cart) {
        order.setDate(LocalDateTime.now());
        order.setStatus(REGISTRATIONS);
        order.setOrderProductList(getOrderProductFromDto(cart, order));
        order.setUser(userService.getCurrentUser());
        return order;
    }

    private List<OrderProduct> getOrderProductFromDto(Cart cart, Order order) {
        return cart.getProducts().entrySet().stream().map(entry -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(productService.getProductById(entry.getKey().getId()));
            orderProduct.setAmount(entry.getValue());
            return orderProduct;
        }).collect(Collectors.toList());
    }
}
