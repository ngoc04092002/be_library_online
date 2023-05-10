package ltw.btl.service.order;

import ltw.btl.model.Orders.OrderEntity;

import java.util.List;

public interface IOrderService {
    List<OrderEntity> getAllOrders();

    List<OrderEntity> getOrderByName(List<String> name);

    Boolean createOrder(OrderEntity orderEntity);

    Boolean deleteAllOrderByName(List<String> name);

    OrderEntity updateOrderQuantity(Long id,Integer quantity);
}
