package ltw.btl.repository.books;


import ltw.btl.model.Orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<OrderEntity,Long> {

    OrderEntity getByNameContainingIgnoreCase(String name);

    @Modifying
    @Query(nativeQuery = true, value = "select * from orders where UPPER(name) in ?1")
    List<OrderEntity> getOrdersByName(List<String>  name);
    OrderEntity getById(Long id);

    @Modifying
    @Query(value = "delete from OrderEntity oe where UPPER(oe.name) in ?1")
    void deleteAllOrderByName(List<String> name);

    void deleteById(Long id);
}
