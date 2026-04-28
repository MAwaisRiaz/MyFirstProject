package com.example.MyFirstProject.repository;

import com.example.MyFirstProject.Entry.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByStudentId(int studentId);
}