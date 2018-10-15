package th.co.scb.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.scb.bookstore.model.Log;
import th.co.scb.bookstore.service.LogService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LogRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private LogRepository logRepository;

    @Test
    public void testDeleteByLogTypeAndUserId() {
		Log log = new Log();
		log.setUserId(1L);
		log.setLogType(LogService.LOG_TYPE_LOGIN);
		log.setUsername("test123");
		log.setCreatedAt(new Date());
		entityManager.persist(log);
        logRepository.deleteByLogTypeAndUserId(LogService.LOG_TYPE_LOGIN, 1L);
        assertThat(entityManager.find(log.getClass(), 1L)).isNull();
    }
    
    @Test
    public void testDeleteByLogTypeAndUserIdShouldDeleteOnlyLoginType() {
    	Log log = new Log();
    	log.setUserId(1L);
    	log.setLogType(LogService.LOG_TYPE_LOGIN);
    	log.setUsername("test123");
    	log.setCreatedAt(new Date());
    	entityManager.persist(log);
    	log = new Log();
    	log.setUserId(1L);
    	log.setLogType(LogService.LOG_TYPE_ORDER);
    	log.setUsername("test123");
    	log.setCreatedAt(new Date());
    	entityManager.persist(log);
    	logRepository.deleteByLogTypeAndUserId(LogService.LOG_TYPE_LOGIN, 1L);
    	assertThat(entityManager.find(log.getClass(), 1L)).isNull();
    	assertThat(entityManager.find(log.getClass(), 2L)).isNotNull();
    }
}
