package com.example.pjboard.repo;

import com.example.pjboard.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findAllById(Long id);
}