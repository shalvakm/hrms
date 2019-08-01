package com.example.leaveservice.repository;

import com.example.leaveservice.Model.RemLeaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemLeavesRepository extends JpaRepository<RemLeaves, Long> {
}
