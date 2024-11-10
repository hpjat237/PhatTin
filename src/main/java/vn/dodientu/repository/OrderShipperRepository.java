package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Order;
import vn.dodientu.model.OrderShipper;

public interface OrderShipperRepository extends JpaRepository<OrderShipper, Order> {
}