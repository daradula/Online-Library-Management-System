package com.library.borrowing_service.repository;

import com.library.borrowing_service.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserId(Long userId);
}