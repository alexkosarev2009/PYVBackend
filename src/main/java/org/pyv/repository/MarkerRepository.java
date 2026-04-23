package org.pyv.repository;

import org.pyv.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findAllByAuthor_Id(Long authorId);
}
