package ltw.btl.service.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ltw.btl.model.Orders.OrderEntity;
import ltw.btl.repository.books.IOrderRepo;
import ltw.btl.repository.error.ArgumentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService implements IOrderService {

    private final IOrderRepo iOrderRepo;

    @Override
    public List<OrderEntity> getAllOrders() {
        return iOrderRepo.findAll();
    }

    @Override
    public List<OrderEntity> getOrderByName(List<String> name) {
        return iOrderRepo.getOrdersByName(name);
    }

    @Override
    public Boolean createOrder(OrderEntity orderEntity) {
        try {
            final var currentOrder = iOrderRepo.getByNameContainingIgnoreCase(orderEntity.getName());

            if (currentOrder != null && orderEntity.getBooks()
                    .getId() == currentOrder.getBooks()
                    .getId()) {
                currentOrder.setQuantity(currentOrder.getQuantity() + orderEntity.getQuantity());
                currentOrder.setAddress(orderEntity.getAddress());
                currentOrder.setTel(orderEntity.getTel());
                iOrderRepo.save(currentOrder);
                return true;
            }
            iOrderRepo.save(orderEntity);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public Boolean deleteAllOrderByName(List<String> names) {
        try {
             iOrderRepo.deleteAllOrderByName(names);
             return true;
        } catch (Exception ex) {
            throw new ArgumentException("Sản phầm đã được thanh toán!");
        }
    }

    @Override
    public OrderEntity updateOrderQuantity(Long id, Integer quantity,Boolean haveAdd) {
        final var currentOrder = iOrderRepo.getById(id);

        if(currentOrder==null){
            throw new ArgumentException("Sản phầm đã được thanh toán!");
        }

        int currentQuantity = currentOrder.getQuantity();
        if (quantity <= 0 || (!haveAdd && quantity > currentQuantity)) {
            throw new ArgumentException("Điều chỉnh lại số lượng");
        }

        int newQuantity = haveAdd ? currentQuantity + quantity : currentQuantity - quantity;
        currentOrder.setQuantity(newQuantity);
        if (newQuantity == 0) {
            iOrderRepo.deleteById(id);
        } else {
            iOrderRepo.save(currentOrder);
        }

        return currentOrder;
    }

    @Override
    public Boolean deleteOrderById(Long id, Integer quantity) {
        try {
            final var currentOrder = iOrderRepo.getById(id);

            if(currentOrder==null || currentOrder.getQuantity()!=quantity){
                throw new ArgumentException("Người dùng đã thay đổi số lượng");
            }
            iOrderRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            System.out.println("order=>>>"+ex.getMessage());
            return false;
        }
    }
}
