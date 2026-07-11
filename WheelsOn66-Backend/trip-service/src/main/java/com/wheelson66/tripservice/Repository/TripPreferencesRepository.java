package com.wheelson66.tripservice.Repository;

import com.wheelson66.tripservice.Entity.TripPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripPreferencesRepository extends JpaRepository<TripPreferences, UUID> {
}
