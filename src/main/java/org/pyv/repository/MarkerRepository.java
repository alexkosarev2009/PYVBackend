package org.pyv.repository;

import org.pyv.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    Optional<Marker> findMarkerByTitle(String title);

    <T> Optional<T> findAllByTitle(String title);
}
