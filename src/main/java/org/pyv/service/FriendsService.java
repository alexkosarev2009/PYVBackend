package org.pyv.service;

import org.pyv.dto.FriendsRequestsDTO;
import org.pyv.dto.UserDTO;
import org.pyv.entity.FriendsRequests;
import org.pyv.entity.FriendsRequestsStatus;
import org.pyv.entity.User;

import java.util.List;

public interface FriendsService {
    List<FriendsRequestsDTO> findByReceiver_IdAndStatus(
            Long receiverId,
            FriendsRequestsStatus status
    );
    List<FriendsRequestsDTO> findAllBySender_IdAndStatus(
            Long senderId,
            FriendsRequestsStatus status
    );
    List<UserDTO> findAllFriends(Long user_Id);
    boolean existsBySender_IdAndReceiver_Id(
            Long senderId,
            Long receiverId
    );
    FriendsRequestsDTO findByReceiver_IdAndSender_IdAndStatus(
            Long receiverId,
            Long senderId,
            FriendsRequestsStatus status
    );
    void declineRequest(Long id);

    void acceptRequest(Long id);

    FriendsRequestsDTO invite(Long receiverId, User user);
}
