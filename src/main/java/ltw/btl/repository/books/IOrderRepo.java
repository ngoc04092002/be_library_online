package ltw.btl.repository.books;


import ltw.btl.model.Orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "select * from orders where UPPER(name) in ?1 and status=0")
    List<OrderEntity> getOrdersByName(List<String> name);

    OrderEntity getById(Long id);

    OrderEntity getByNameContainingIgnoreCaseAndStatusAndBooks_Id(String name,Integer status,Long id);

    @Modifying
    @Query(value = "delete from OrderEntity oe where oe.id in ?1")
    void deleteAllOrderByIds(List<Long> ids);

    @Modifying
    @Query(nativeQuery = true, value = "update orders set status=?1 where id in ?2")
    void updateStatus(Integer status, List<Long> id);

    void deleteById(Long id);
}
