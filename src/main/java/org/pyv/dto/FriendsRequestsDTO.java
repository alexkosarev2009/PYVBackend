package org.pyv.dto;

import lombok.Data;
import org.pyv.entity.FriendsRequestsStatus;

import java.time.Instant;

@Data
public class FriendsRequestsDTO {
    private long id;
    private long senderId;
    private long receiverId;
    private FriendsRequestsStatus status;
    private Instant createdAt;
}
