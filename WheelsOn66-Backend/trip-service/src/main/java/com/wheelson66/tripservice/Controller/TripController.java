package com.wheelson66.tripservice.Controller;

import com.wheelson66.tripservice.DTO.StepRequest;
import com.wheelson66.tripservice.DTO.TripCreateRequest;
import com.wheelson66.tripservice.DTO.TripResponse;
import com.wheelson66.tripservice.Entity.Step;
import com.wheelson66.tripservice.Entity.Trip;
import com.wheelson66.tripservice.Service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {
     private final TripService tripService;

     @PostMapping
    public ResponseEntity<TripResponse>createTrip(
             @RequestHeader("X-User-Id") Long userId,
             @Valid @RequestBody TripCreateRequest request
     ){
         Trip newTrip = tripService.createTrip(userId,request.title(),request.visibility());
         TripResponse response = new TripResponse(
                 newTrip.getId(),
                 newTrip.getTripHash(),
                 newTrip.getTitle(),
                 newTrip.getVisibility(),
                 newTrip.getStatus(),
                 newTrip.getTotalDistanceKm(),
                 newTrip.getTotalDurationMinutes(),
                 new ArrayList<>()
         );
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }
     @PostMapping("/{tripId}/steps")
    public ResponseEntity<Step> addStep(
             @PathVariable UUID tripId,
             @RequestBody StepRequest request
             ){
         Step newStep = tripService.addStepToTrip(tripId,request);
         return ResponseEntity.status(HttpStatus.CREATED).body(newStep);
     }

}
