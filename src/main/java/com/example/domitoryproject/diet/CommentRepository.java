package com.example.domitoryproject.diet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query("SELECT C FROM Comment as C WHERE C.id= :id")
//    Comment findById(@Param("id")Long id);
}
