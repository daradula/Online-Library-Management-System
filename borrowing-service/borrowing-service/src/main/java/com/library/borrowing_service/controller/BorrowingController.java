package com.library.borrowing_service.controller;

import com.library.borrowing_service.model.Borrowing;
import com.library.borrowing_service.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingRepository repository;

    // 1. Borrow a book
    @PostMapping
    public Borrowing borrowBook(@RequestBody Borrowing borrowing) {
        borrowing.setStatus("BORROWED");
        return repository.save(borrowing);
    }

    // 2. View all borrowings
    @GetMapping
    public List<Borrowing> getAllBorrowings() {
        return repository.findAll();
    }

    // 3. View borrowing history by user
    @GetMapping("/user/{userId}")
    public List<Borrowing> getByUser(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    // 4. Return a book
    @PutMapping("/{id}/return")
    public Borrowing returnBook(@PathVariable Long id, @RequestParam String returnDate) {
        Borrowing b = repository.findById(id).orElseThrow();
        b.setStatus("RETURNED");
        b.setReturnDate(returnDate);
        return repository.save(b);
    }
}