package th.co.scb.bookstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import th.co.scb.bookstore.model.Book;
import th.co.scb.bookstore.util.DataUtil;

@Service
public class BookServiceImpl implements BookService {
	
	private final String URL_ALL_BOOKS = "https://scb-test-book-publisher.herokuapp.com/books";
	private final String URL_RECOMMEND_BOOKS = "https://scb-test-book-publisher.herokuapp.com/books/recommendation";
	
	@Override
	public List<Book> listBooks() {
		List<Book> books = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		Book[] recommendBooks = restTemplate.getForObject(URL_RECOMMEND_BOOKS, Book[].class);
		Book[] allBooks = restTemplate.getForObject(URL_ALL_BOOKS, Book[].class);
		addBooks(books, recommendBooks, true);
		addBooks(books, allBooks, false);
		return books;
	}
	
	private void addBooks(List<Book> books, Book[] extBooks, boolean isRerecommended) {
		if (DataUtil.isEmpty(extBooks)) {
			return;
		}
		sortBookByAlphabet(extBooks);
		for (Book extBook : extBooks) {
			if (books.contains(extBook)) {
				continue;
			}
			extBook.setIs_recommended(isRerecommended);
			extBook.setPrice(extBook.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
			books.add(extBook);
		}
	}
	
	private void sortBookByAlphabet(Book[] books) {
		Arrays.sort(books, new Comparator<Book>() {
			@Override
	        public int compare(Book b1, Book b2) {
	            return b1.getBook_name().compareTo(b2.getBook_name());
	        }
		});
	}
}
