package th.co.scb.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import th.co.scb.bookstore.exception.ApplicationErrorException;
import th.co.scb.bookstore.exception.InputFieldEmptyException;
import th.co.scb.bookstore.exception.InputFieldNotValidException;
import th.co.scb.bookstore.exception.ResourceNotFoundException;
import th.co.scb.bookstore.model.OrderDtl;
import th.co.scb.bookstore.model.OrderMst;
import th.co.scb.bookstore.model.User;
import th.co.scb.bookstore.payload.OrderRequest;
import th.co.scb.bookstore.payload.OrderResponse;
import th.co.scb.bookstore.payload.UserRequest;
import th.co.scb.bookstore.payload.UserResponse;
import th.co.scb.bookstore.service.SecurityService;
import th.co.scb.bookstore.service.UserService;
import th.co.scb.bookstore.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "user", tags = "User API")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;

	@PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create a user account and store user’s information")
	public void createUser(@RequestBody UserRequest request) {
		if (DataUtil.isEmpty(request.getUsername())) {
			throw new InputFieldEmptyException("username", "username is require field");
		}
		if (userService.findByUsername(request.getUsername()) != null) {
			throw new InputFieldNotValidException("username", "username already exists");
		}
		if (DataUtil.isEmpty(request.getPassword())) {
			throw new InputFieldEmptyException("password", "password is require field");
		}
		if (!DataUtil.isEmpty(request.getDate_of_birth()) 
				&& !DataUtil.isDate(request.getDate_of_birth(), DataUtil.DATE_FORMAT_DDMMYYYY)) {
			throw new InputFieldNotValidException("date_of_birth", "format date is DD/MM/YYYY");
		}
		try {
			User user = new User(request.getUsername(), request.getPassword(), request.getName(),
					request.getSurname(), DataUtil.toDate(request.getDate_of_birth(), DataUtil.DATE_FORMAT_DDMMYYYY));
			userService.createUser(user);
		} catch (Exception e) {
			throw new ApplicationErrorException("create user");
		}
	}
	
	@GetMapping("/users")
	@ResponseBody
	@ApiOperation(value = "Gets information about the logged user, information user and books ordered (login required)",
			response = UserResponse.class)
	public UserResponse getUserInfo() {
		User user = null;
		List<OrderMst> orderMsts = new ArrayList<OrderMst>();
		try {
			String username = securityService.getLoggedInUsername();
			user = userService.findByUsername(username);
			orderMsts = userService.listBookOrders(user);
			return new UserResponse(user, orderMsts);
		} catch (Exception e) {
			throw new ApplicationErrorException("get user info and book ordered");
		}
	}
	
	@DeleteMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete logged in user’s record and order history (login required)")
	public void deleteUserLogged() {
		try {
			String username = securityService.getLoggedInUsername();
			User user = userService.findByUsername(username);
			userService.deleteUserLogged(user);
		} catch (Exception e) {
			throw new ApplicationErrorException("delete user logged in and order history");
		}
	}
	
	@PostMapping(path = "/users/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Order books and store order information (login required)",
			response = OrderResponse.class)
	public OrderResponse orderBooks(@RequestBody OrderRequest request) {
		if (request.getOrders() == null || request.getOrders().isEmpty()) {
			throw new InputFieldEmptyException("orders", "orders is require field");
		}
		try {
			String username = securityService.getLoggedInUsername();
			User user = userService.findByUsername(username);
			OrderMst orderMst = userService.createOrderBooks(request.getOrders(), user);
			List<Long> bookIds = new ArrayList<Long>();
			for (OrderDtl orderDtl : orderMst.getOrderDtls()) {
				bookIds.add(orderDtl.getBookId());
			}
			return new OrderResponse(orderMst.getOrderMstId(), orderMst.getOrderDate(), 
					orderMst.getTotalPrice(), bookIds);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationErrorException("order books");
		}
	}
}
