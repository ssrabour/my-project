package th.co.scb.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.co.scb.bookstore.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	
	void deleteByLogTypeAndUserId(String logType, Long userId);
}
