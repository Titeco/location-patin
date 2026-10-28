package com.example.demo.repository;

import com.example.demo.dto.SkateDto;
import com.example.demo.entity.SkateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkateRepository extends JpaRepository<SkateEntity,Long> {
    List<SkateEntity> findByUserNotNull();
    List<SkateEntity> findBySizeAndUserNotNull(Double size);
    List<SkateEntity> findBySize(Double size);
}
