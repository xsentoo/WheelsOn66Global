package com.wheelson66.tripservice.Repository;

import com.wheelson66.tripservice.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findAllByOwnerIdAndIsDeletedFalse(Long ownerId);
    Optional<Trip> findByTripHashAndIsDeletedFalse(String tripHash);
}