package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.Entry.JournalEntry;
import com.example.MyFirstProject.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class EntityController {


    @Autowired
    private JournalEntryService entryService;

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @PostMapping
    public String addEntry(@RequestBody JournalEntry entry) {
        entryService.saveNewEntry(entry);
        return "Entry Added Successfully";
    }
}