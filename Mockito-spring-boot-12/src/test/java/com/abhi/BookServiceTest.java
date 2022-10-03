package com.abhi;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abhi.MockitoSpringBoot12Application;
import com.abhi.entity.Book;
import com.abhi.repository.BookRepository;
import com.abhi.service.BookService;

@SpringBootTest(classes=MockitoSpringBoot12Application.class)
public class BookServiceTest {
	
	@Mock 
	BookRepository bookRepo;
	
	@InjectMocks
	BookService bookService = new BookService();
	
	@Test
	public void testGetAllBooks() {
		
		List<Book> books = new ArrayList<>();
		Book b = new Book(1, "Learn Java", "Abc", 100, "borrowed");
		Book b1 = new Book(2, "Learn Java1", "Abc1", 10, "borrowed");
		books.add(b);
		books.add(b1);
		
		when(bookRepo.findAll()).thenReturn(books);
		List<Book> result = bookService.getAllBooks();
		
		Assertions.assertNotEquals(null, result);
		Assertions.assertTrue(result.get(0).getName().endsWith("-b"));
		Assertions.assertEquals("Learn Java-b", result.get(0).getName());
		
		Assertions.assertTrue(result.get(1).getName().endsWith("-b"));
		Assertions.assertEquals("Learn Java1-b", result.get(1).getName());
		
		Assertions.assertTrue(result.get(0).getAuthor().endsWith("-a"));
		Assertions.assertEquals("Abc-a", result.get(0).getAuthor());
		
		Assertions.assertTrue(result.get(1).getAuthor().endsWith("-a"));
		Assertions.assertEquals("Abc1-a", result.get(1).getAuthor());
	}
}
