package com.wheelson66.tripservice.Repository;

import com.wheelson66.tripservice.Entity.Enums.JoinStatus;
import com.wheelson66.tripservice.Entity.TripMember;

import java.util.List;
import java.util.UUID;

public interface TripMemberRepository {
    List<TripMember> findByTripIdAndIsDeletedFalse(UUID tripId);
    List<TripMember> findByUserIdAndJoinStatusAndIsDeletedFalse(Long userId, JoinStatus joinStatus);
}
