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
    public OrderEntity updateOrderQuantity(@RequestParam Long id,@RequestParam Integer quantity,
            @RequestParam Boolean haveAdd){
        return  iOrderService.updateOrderQuantity(id,quantity, haveAdd);
    }

    @PutMapping("update-status")
    public Boolean updateStatus(@RequestParam Integer status,@RequestBody List<Long> id){
        return iOrderService.updateStatus(status,id);
    }

    @PostMapping("delete-order")
    public Boolean deleteOrdersByIds(@RequestBody List<Long> ids){
        return iOrderService.deleteAllOrderByIds(ids);
    }

    @DeleteMapping("delete-order/{id}")
    public Boolean deleteOrderById(@PathVariable Long id,@RequestParam Integer quantity){
        return iOrderService.deleteOrderById(id, quantity);
    }
}
