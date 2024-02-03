package com.serviceApp.repo;

import com.serviceApp.entity.BlackLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackLineRepo extends JpaRepository<BlackLine, Long> {
}
