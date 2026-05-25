package org.pyv.repository;

import org.pyv.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    @Query("""
            SELECT m
            FROM Marker m
            WHERE
                m.author.id = :authorId
                AND (
                    :userId = :authorId
                    OR m.visibility = 1
                    OR (
                        m.visibility = 2
                        AND EXISTS (
                            SELECT 1
                            FROM FriendsRequests f
                            WHERE
                                f.status = org.pyv.entity.FriendsRequestsStatus.ACCEPTED
                                AND (
                                    (f.sender.id = :userId AND f.receiver.id = :authorId)
                                    OR
                                    (f.receiver.id = :userId AND f.sender.id = :authorId)
                                )
                        )
                    )
                )
            ORDER BY m.createdAt DESC
            """)
    List<Marker> findAllVisibleAuthorMarkers(
            Long authorId,
            Long userId
    );

    @Query("""
                SELECT m
                FROM Marker m
                WHERE
                    m.visibility = 1
                    OR (
                        m.visibility = 2
                        AND EXISTS (
                            SELECT 1
                            FROM FriendsRequests f
                            WHERE
                                f.status = org.pyv.entity.FriendsRequestsStatus.ACCEPTED
                                AND (
                                    (f.sender.id = :authorId AND f.receiver.id = m.author.id)
                                    OR
                                    (f.receiver.id = :authorId AND f.sender.id = m.author.id)
                                )
                        )
                    )
                    AND NOT m.author.id = :authorId
                ORDER BY m.createdAt DESC
            """)
    List<Marker> findByAuthor_IdNotOrderByCreatedAtDesc(Long authorId);

    Optional<Marker> findByIdAndAuthor_Id(Long id, Long authorId);

    @Query("""
                SELECT m
                FROM Marker m
                WHERE
                    m.visibility = 1
                    OR (
                        m.visibility = 2
                        AND EXISTS (
                            SELECT 1
                            FROM FriendsRequests f
                            WHERE
                                f.status = org.pyv.entity.FriendsRequestsStatus.ACCEPTED
                                AND (
                                    (f.sender.id = :userId AND f.receiver.id = m.author.id)
                                    OR
                                    (f.receiver.id = :userId AND f.sender.id = m.author.id)
                                )
                        )
                    )
                ORDER BY m.createdAt DESC
            """)
    List<Marker> findAvailableMarkers(Long userId);

    @Query("""
                SELECT m
                FROM Marker m
                WHERE
                    EXISTS (
                            SELECT 1
                            FROM FriendsRequests f
                            WHERE
                                f.status = org.pyv.entity.FriendsRequestsStatus.ACCEPTED
                                AND (
                                    (f.sender.id = :userId AND f.receiver.id = m.author.id)
                                    OR
                                    (f.receiver.id = :userId AND f.sender.id = m.author.id)
                                )
                        )
                ORDER BY m.createdAt DESC
            """)
    List<Marker> findFriendsMarkers(Long userId);

}
