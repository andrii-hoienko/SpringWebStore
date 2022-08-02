package ua.hoienko.webstorespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hoienko.webstorespring.model.Order;
import ua.hoienko.webstorespring.model.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    @Transactional
    @Modifying
    @Query("update Order o set o.status = ?1 where o.id = ?2")
    int updateStatusById(Status status, Long id);

}
