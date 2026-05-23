package org.pyv.repository;

import org.pyv.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findAllByAuthor_Id(Long authorId);
    List<Marker> findByAuthor_IdNotOrderByCreatedAtDesc(Long authorId);
    Optional<Marker> findByIdAndAuthor_Id(Long id, Long authorId);
}
