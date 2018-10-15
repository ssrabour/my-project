package th.co.scb.bookstore.payload;

import java.io.Serializable;
import java.util.List;

import th.co.scb.bookstore.model.Book;

public class BookResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Book> books;

	public BookResponse(List<Book> books) {
		this.books = books;
	}
	
	public List<Book> getBooks() {
		return books;
	}
}
