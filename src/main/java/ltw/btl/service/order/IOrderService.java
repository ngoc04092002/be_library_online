package ltw.btl.service.order;

import ltw.btl.model.Orders.OrderEntity;

import java.util.List;

public interface IOrderService {
    List<OrderEntity> getAllOrders();

    List<OrderEntity> getOrderByName(List<String> name);

    Boolean createOrder(OrderEntity orderEntity);

    Boolean deleteAllOrderByIds(List<Long> ids);

    OrderEntity updateOrderQuantity(Long id,Integer quantity, Boolean haveAdd);

    Boolean updateStatus(Integer status,List<Long> id);

    Boolean deleteOrderById(Long id,Integer quantity);

}
