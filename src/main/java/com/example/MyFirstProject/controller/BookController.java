package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.Entry.Book;
import com.example.MyFirstProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted successfully!";
    }

    @PutMapping("/{bookId}/assign/{studentId}")
    public Book assignBook(@PathVariable int bookId, @PathVariable int studentId) {
        return bookService.assignBookToStudent(bookId, studentId);
    }

    @PutMapping("/{bookId}/unassign")
    public Book unassignBook(@PathVariable int bookId) {
        return bookService.unassignBook(bookId);
    }
}