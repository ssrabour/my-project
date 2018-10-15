package th.co.scb.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import th.co.scb.bookstore.exception.ApplicationErrorException;
import th.co.scb.bookstore.payload.BookResponse;
import th.co.scb.bookstore.service.BookService;

@RestController
@RequestMapping("/api")
@Api(value = "book", tags = "Book API")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	@ResponseBody
	@ApiOperation(value = "Gets a list of books from an external book publisher’s web services", 
			response = BookResponse.class)
	public BookResponse listBooks() {
		try {
			return new BookResponse(bookService.listBooks());
		} catch (Exception e) {
			throw new ApplicationErrorException("list books");
		}
	}
}
