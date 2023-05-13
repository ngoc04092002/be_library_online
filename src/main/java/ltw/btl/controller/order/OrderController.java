package ltw.btl.controller.order;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.Orders.OrderEntity;
import ltw.btl.service.order.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class OrderController {
    private final IOrderService iOrderService;

    @GetMapping("orders")
    public List<OrderEntity> getAllOrders(){
        return iOrderService.getAllOrders();
    }

    @GetMapping("orders-name")
    public List<OrderEntity> getOrdersByName(@RequestParam List<String> name){
        return iOrderService.getOrderByName(name);
    }

    @PostMapping("add-order")
    public Boolean createOrder(@RequestBody OrderEntity orderEntity){
        return iOrderService.createOrder(orderEntity);
    }

    @PutMapping("update-order")
    public OrderEntity updateOrderQuantity(@RequestParam Long id,@RequestParam Integer quantity){
        return  iOrderService.updateOrderQuantity(id,quantity);
    }

    @PostMapping("delete-order")
    public Boolean deleteAllOrderByName(@RequestBody List<String> names){
        return iOrderService.deleteAllOrderByName(names);
    }

    @DeleteMapping("delete-order/{id}")
    public Boolean deleteOrderById(@PathVariable Long id){
        return iOrderService.deleteOrderById(id);
    }
}
