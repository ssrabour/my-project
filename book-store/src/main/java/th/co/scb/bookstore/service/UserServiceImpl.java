package th.co.scb.bookstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import th.co.scb.bookstore.exception.ResourceNotFoundException;
import th.co.scb.bookstore.model.Book;
import th.co.scb.bookstore.model.Log;
import th.co.scb.bookstore.model.OrderDtl;
import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;
import th.co.scb.bookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void deleteUserLogged(User user) {
		logService.deleteByLogTypeAndUserId(LogService.LOG_TYPE_LOGIN, user.getUserId());
		logService.deleteByLogTypeAndUserId(LogService.LOG_TYPE_ORDER, user.getUserId());
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<OrderMst> listBookOrders(User user) {
		return orderService.findByUserId(user.getUserId());
	}
	
	@Override
	@Transactional
	public OrderMst createOrderBooks(List<Long> bookIds, User user) {
		Map<Long, Book> bookOrderById = new HashMap<Long, Book>();
		Map<Long, Book> bookListById = getBookListById(bookService.listBooks());
		for (Long bookId : bookIds) {
			if (!bookListById.containsKey(bookId)) {
				throw new ResourceNotFoundException("Book", "id", bookId.toString());
			}
			Book book = bookListById.get(bookId);
			long qty = (book.getQty() == null) ? 0 : book.getQty().longValue();
			book.setQty(Long.valueOf(qty + 1));
			bookOrderById.put(bookId, book);
		}
		List<OrderDtl> orderDtls = new ArrayList<OrderDtl>();
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Book book : bookOrderById.values()) {
			orderDtls.add(new OrderDtl(book.getId(), book.getQty(), book.getPrice()));
			totalPrice = totalPrice.add(book.getPrice().multiply(new BigDecimal(book.getQty().longValue())));
		}
		OrderMst orderMst = new OrderMst();
		orderMst.setOrderDate(new Date());
		orderMst.setTotalPrice(totalPrice);
		orderMst = orderService.createOrder(orderMst, orderDtls, user);
		Log log = new Log(LogService.LOG_TYPE_ORDER, user.getUsername(), user.getUserId(), orderMst.getOrderMstId());
		logService.createLog(log);
		return orderMst;
	}
	
	private Map<Long, Book> getBookListById(List<Book> books) {
		Map<Long, Book> bookListById = new HashMap<Long, Book>();
		for (Book book : books) {
			bookListById.put(book.getId(), book);
		}
		return bookListById;
	}
}
