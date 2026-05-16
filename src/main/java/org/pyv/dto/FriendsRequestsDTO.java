package org.pyv.dto;

import lombok.Data;
import org.pyv.entity.FriendsRequestsStatus;

import java.time.Instant;

@Data
public class FriendsRequestsDTO {
    private long id;
    private long sender_id;
    private long receiver_id;
    private FriendsRequestsStatus status;
    private Instant createdAt;
}
