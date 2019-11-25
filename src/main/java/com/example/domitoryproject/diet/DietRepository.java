package com.example.domitoryproject.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DietRepository extends JpaRepository<Diet, String> {

    @Query("SELECT d FROM Diet as d WHERE d.id= :id")
    Diet findById(@Param("id") Long id);
}
