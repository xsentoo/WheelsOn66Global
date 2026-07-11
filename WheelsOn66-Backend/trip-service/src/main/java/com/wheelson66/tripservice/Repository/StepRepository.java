package com.wheelson66.tripservice.Repository;

import com.wheelson66.tripservice.Entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {

    List<Step> findByTripIdAndIsDeletedFalseOrderByOrderIndexAsc(UUID tripId);
}
