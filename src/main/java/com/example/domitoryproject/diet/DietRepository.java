package com.example.domitoryproject.diet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet, String> {
}
