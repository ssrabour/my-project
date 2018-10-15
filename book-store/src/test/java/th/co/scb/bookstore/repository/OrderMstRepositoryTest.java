package th.co.scb.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.scb.bookstore.model.OrderMst;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderMstRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private OrderMstRepository orderMstRepository;

    @Test
    public void testFindByUserId() {
    	OrderMst orderMst = new OrderMst();
        orderMst.setUserId(1L);
        orderMst.setCreatedAt(new Date());
        orderMst.setUpdatedAt(new Date());
        entityManager.persist(orderMst);
        List<OrderMst> results = orderMstRepository.findByUserId(1L);
        assertThat(results.get(0).getUserId()).isEqualTo(1L);
    }
    
    @Test
    public void testFindByUserIdShouldBeEmpty() {
    	OrderMst orderMst = new OrderMst();
    	orderMst.setUserId(1L);
    	orderMst.setCreatedAt(new Date());
    	orderMst.setUpdatedAt(new Date());
    	entityManager.persist(orderMst);
    	List<OrderMst> results = orderMstRepository.findByUserId(2L);
    	assertThat(results).isNotNull();
    	assertThat(results).isEmpty();
    }
}
