package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Entry.Book;
import com.example.MyFirstProject.Entry.Student;
import com.example.MyFirstProject.repository.BookRepository;
import com.example.MyFirstProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }


    public Book addBook(Book book) {

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Book title should not be empty");
        }

        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new RuntimeException("Book author should not be empty");
        }

        book.setTitle(book.getTitle().trim());
        book.setAuthor(book.getAuthor().trim());

        return bookRepository.save(book);
    }


    public Book updateBook(int id, Book updatedBook) {

        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle().trim());
        existingBook.setAuthor(updatedBook.getAuthor().trim());

        return bookRepository.save(existingBook);
    }


    public void deleteBook(int id) {

        getBookById(id);

        bookRepository.deleteById(id);
    }


    public Book assignBookToStudent(int bookId, int studentId) {

        Book book = getBookById(bookId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        if (book.getStudent() != null) {
            throw new RuntimeException(" The Book is already assign to"
                    + book.getStudent().getName());
        }

        book.setStudent(student);

        return bookRepository.save(book);
    }


    public Book unassignBook(int bookId) {

        Book book = getBookById(bookId);

        if (book.getStudent() == null) {
            throw new RuntimeException("This book is not assigned");
        }

        book.setStudent(null);

        return bookRepository.save(book);
    }
}