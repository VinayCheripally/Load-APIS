package com.example.demo.repository;

import com.example.demo.model.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<Load, String> {
    List<Load> findByShipperId(String shipperId);
}
