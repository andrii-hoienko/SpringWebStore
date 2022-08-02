package ua.hoienko.webstorespring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hoienko.webstorespring.mapper.OrderMapper;
import ua.hoienko.webstorespring.model.Cart;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.Status;
import ua.hoienko.webstorespring.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public void createOrder(Cart cart) {
        Order order = new Order();
        orderMapper.map(order, cart);
        orderRepository.save(order);
    }

    public void changeStatus(String status, long orderId) {
        orderRepository.updateStatusById(Status.valueOf(status), orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
