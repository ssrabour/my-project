package th.co.scb.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.scb.bookstore.model.OrderMst;

@Repository
public interface OrderMstRepository extends JpaRepository<OrderMst, Long> {
	
	List<OrderMst> findByUserId(Long userId);
}
