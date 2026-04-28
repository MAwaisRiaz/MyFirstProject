package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Entry.JournalEntry;
import com.example.MyFirstProject.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;


    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public JournalEntry saveNewEntry(JournalEntry entry) {


        if (entry.getTitle().isEmpty()) {
            throw new RuntimeException(" Title cannot be empty");
        }

        return repository.save(entry);
    }

}
