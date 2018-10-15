package th.co.scb.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.scb.bookstore.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("test123");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        entityManager.persist(user);
        User result = userRepository.findByUsername("test123");
        assertThat(result.getUsername()).isEqualTo("test123");
    }
    
    @Test
    public void testFindByUsernameShouldBeNull() {
    	User user = new User();
    	user.setUsername("test123");
    	user.setCreatedAt(new Date());
    	user.setUpdatedAt(new Date());
    	entityManager.persist(user);
    	User result = userRepository.findByUsername("test321");
    	assertThat(result).isNull();
    }
}
