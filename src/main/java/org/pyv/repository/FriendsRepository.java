package org.pyv.repository;

import org.pyv.entity.FriendsRequests;
import org.pyv.entity.FriendsRequestsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<FriendsRequests, Long> {
    List<FriendsRequests> findAllByReceiver_IdAndStatusOrderByCreatedAtDesc(
            Long receiverId,
            FriendsRequestsStatus status
    );
    List<FriendsRequests> findAllBySender_IdAndStatusOrderByCreatedAtDesc(
            Long senderId,
            FriendsRequestsStatus status
    );
    FriendsRequests findByReceiver_IdAndSender_IdAndStatus(
            Long receiverId,
            Long senderId,
            FriendsRequestsStatus status
    );
    boolean existsBySender_IdAndReceiver_Id(
            Long sender,
            Long receiverId
    );
    void deleteByReceiver_IdAndSender_Id(
            Long senderId,
            Long receiverId
    );

}
