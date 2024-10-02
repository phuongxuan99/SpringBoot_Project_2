package com.example.pjboard.repo;

import com.example.pjboard.model.Board;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByName(String name);
}