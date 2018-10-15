package th.co.scb.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.scb.bookstore.model.OrderDtl;

@Repository
public interface OrderDtlRepository extends JpaRepository<OrderDtl, Long> {
	
}
