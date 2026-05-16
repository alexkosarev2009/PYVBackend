package org.pyv.util;

import lombok.experimental.UtilityClass;
import org.pyv.dto.FriendsRequestsDTO;
import org.pyv.entity.FriendsRequests;

@UtilityClass
public class FriendsMapper {
    public FriendsRequestsDTO friendRequestToDTO(FriendsRequests friendsRequests) {
        FriendsRequestsDTO dto = new FriendsRequestsDTO();
        dto.setId(friendsRequests.getId());
        dto.setStatus(friendsRequests.getStatus());
        dto.setSenderId(friendsRequests.getSender().getId());
        dto.setReceiverId(friendsRequests.getReceiver().getId());
        dto.setCreatedAt(friendsRequests.getCreatedAt());
        return dto;
    }
}
