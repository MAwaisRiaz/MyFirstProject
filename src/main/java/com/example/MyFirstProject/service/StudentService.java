package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Entry.Book;
import com.example.MyFirstProject.Entry.Student;
import com.example.MyFirstProject.repository.BookRepository;
import com.example.MyFirstProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


    public Student addStudent(Student student) {

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new RuntimeException("Student name could not be empty");
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Student email could not be empty");
        }

        student.setName(student.getName().trim());
        student.setEmail(student.getEmail().trim());

        return studentRepository.save(student);
    }


    public Student updateStudent(int id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(updatedStudent.getName().trim());
        existingStudent.setEmail(updatedStudent.getEmail().trim());

        return studentRepository.save(existingStudent);
    }


    public void deleteStudent(int id) {

        Student student = getStudentById(id);

        List<Book> books = bookRepository.findByStudentId(id);
        for (Book book : books) {
            book.setStudent(null);
            bookRepository.save(book);
        }

        studentRepository.deleteById(id);
    }


    public List<Book> getBooksByStudentId(int studentId) {

        getStudentById(studentId);

        return bookRepository.findByStudentId(studentId);
    }
}