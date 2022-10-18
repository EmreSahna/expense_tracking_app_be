package com.emresahna.loginwithreact.Repository;

import com.emresahna.loginwithreact.Entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
