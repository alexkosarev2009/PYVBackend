package org.pyv.controller;

import lombok.RequiredArgsConstructor;
import org.pyv.dto.FriendsRequestsDTO;
import org.pyv.dto.UserDTO;
import org.pyv.entity.FriendsRequestsStatus;
import org.pyv.entity.User;
import org.pyv.service.FriendsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsService friendsService;

    @GetMapping("/invitations")
    public ResponseEntity<List<FriendsRequestsDTO>> getMyInvitations(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(friendsService.findByReceiver_IdAndStatus(user.getId(), FriendsRequestsStatus.PENDING));
    }

    @GetMapping("/invitations/{receiver_Id}")
    public ResponseEntity<List<FriendsRequestsDTO>> getInvitationsByReceiverId(@PathVariable Long receiver_Id) {
        return ResponseEntity.ok(friendsService.findByReceiver_IdAndStatus(receiver_Id, FriendsRequestsStatus.PENDING));
    }

    @DeleteMapping("/invitations/{id}/decline")
    public ResponseEntity<Void> declineRequest(@PathVariable Long id) {
        friendsService.declineRequest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/invitations/{id}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long id) {
        friendsService.acceptRequest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/invite/{receiver_Id}")
    public ResponseEntity<FriendsRequestsDTO> inviteFriend(@PathVariable Long receiver_Id,
                                             @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(friendsService.invite(receiver_Id, user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getMyFriends(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(friendsService.findAllFriends(user.getId()));
    }

    @GetMapping("/{user_Id}")
    public ResponseEntity<List<UserDTO>> getFriendsByUserId(@PathVariable Long user_Id) {
        return ResponseEntity.ok(friendsService.findAllFriends(user_Id));
    }
}
