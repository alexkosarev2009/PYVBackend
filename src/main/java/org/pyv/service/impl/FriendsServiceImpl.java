package org.pyv.service.impl;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.FriendsRequestsDTO;
import org.pyv.dto.UserDTO;
import org.pyv.entity.FriendsRequests;
import org.pyv.entity.FriendsRequestsStatus;
import org.pyv.entity.User;
import org.pyv.repository.FriendsRepository;
import org.pyv.repository.UserRepository;
import org.pyv.service.FriendsService;
import org.pyv.util.FriendsMapper;
import org.pyv.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    @Override
    public List<FriendsRequestsDTO> findByReceiver_IdAndStatus(Long receiverId, FriendsRequestsStatus status) {
        return friendsRepository.findAllByReceiver_IdAndStatus(receiverId, status)
                .stream()
                .map(FriendsMapper::friendRequestToDTO)
                .toList();
    }

    @Override
    public List<FriendsRequestsDTO> findAllBySender_IdAndStatus(Long senderId, FriendsRequestsStatus status) {
        return friendsRepository.findAllByReceiver_IdAndStatus(senderId, status)
                .stream()
                .map(FriendsMapper::friendRequestToDTO)
                .toList();
    }

    @Override
    public List<UserDTO> findAllFriends(User user) {
        ArrayList<UserDTO> friends = new ArrayList<>();

        List<FriendsRequests> received = friendsRepository.findAllByReceiver_IdAndStatus(
                user.getId(), FriendsRequestsStatus.ACCEPTED
        );
        for (FriendsRequests request: received) {
            friends.add(UserMapper.userToDTO(request.getSender()));
        }

        List<FriendsRequests> sent = friendsRepository.findAllBySender_IdAndStatus(
                user.getId(), FriendsRequestsStatus.ACCEPTED
        );
        for (FriendsRequests request: sent) {
            friends.add(UserMapper.userToDTO(request.getReceiver()));
        }

        return friends;
    }

    @Override
    public FriendsRequestsDTO findByReceiver_IdAndSender_IdAndStatus(Long receiverId, Long senderId, FriendsRequestsStatus status) {
        return FriendsMapper.friendRequestToDTO(friendsRepository.findByReceiver_IdAndSender_IdAndStatus(receiverId, senderId, status));
    }

    @Override
    public boolean existsBySender_IdAndReceiver_Id(Long senderId, Long receiverId) {
        return friendsRepository.existsBySender_IdAndReceiver_Id(senderId, receiverId) ||
                friendsRepository.existsBySender_IdAndReceiver_Id(receiverId, senderId);
    }

    @Override
    public void declineRequest(Long id) {
        friendsRepository.deleteById(id);
    }

    @Override
    public void acceptRequest(Long id) {
        FriendsRequests request = friendsRepository.findById(id)
                .orElseThrow();

        request.setStatus(FriendsRequestsStatus.ACCEPTED);

        friendsRepository.save(request);
    }

    @Override
    public FriendsRequestsDTO invite(Long receiverId, User user) {
        boolean hasSent = friendsRepository.existsBySender_IdAndReceiver_Id(user.getId(), receiverId);
        boolean hasGotten = friendsRepository.existsBySender_IdAndReceiver_Id(receiverId, user.getId());
        if (!(hasGotten || hasSent)) {
            FriendsRequests request = new FriendsRequests();
            request.setReceiver(userRepository.findById(receiverId).orElseThrow());
            request.setSender(user);
            request.setStatus(FriendsRequestsStatus.PENDING);
            return FriendsMapper.friendRequestToDTO(friendsRepository.save(request));
        }
        else if (hasGotten) {
            FriendsRequests request = friendsRepository
                    .findByReceiver_IdAndSender_IdAndStatus(
                            user.getId(),
                            receiverId,
                            FriendsRequestsStatus.PENDING
                    );
            if (request != null) {
                request.setStatus(FriendsRequestsStatus.ACCEPTED);
                FriendsMapper.friendRequestToDTO(friendsRepository.save(request));
            }
        }
        return null;
    }

}
