package com.wheelson66.tripservice.Service;

import com.wheelson66.tripservice.DTO.StepRequest;
import com.wheelson66.tripservice.Entity.Enums.TripStatus;
import com.wheelson66.tripservice.Entity.Enums.Visibility;
import com.wheelson66.tripservice.Entity.Step;
import com.wheelson66.tripservice.Entity.Trip;
import com.wheelson66.tripservice.Repository.StepRepository;
import com.wheelson66.tripservice.Repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final StepRepository stepRepository;

    @Transactional
    public Trip createTrip(Long ownerId, String title, Visibility visibility) {
        Trip trip = new Trip();
        trip.setOwnerId(ownerId);
        trip.setTitle(title);
        trip.setVisibility(visibility);
        trip.setStatus(TripStatus.DRAFT);
        trip.setTripHash(generateTripHash());

        return tripRepository.save(trip);

    }
    private String generateTripHash() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @Transactional
    public Step addStepToTrip(UUID tripId, StepRequest request){
        Trip trip = tripRepository.findById(tripId).orElseThrow(()-> new EntityNotFoundException("Trip not found"));
        Point<G2D> geomLocation = Geometries.mkPoint(
                new G2D(request.latitude(),request.longitude()),
                CoordinateReferenceSystems.WGS84
        );

        Step step = Step.builder()
                .trip(trip)
                .locationName(request.locationName())
                .geom(geomLocation)
                .orderIndex(request.orderIndex() != null ? request.orderIndex():1)
                .build();
        return stepRepository.save(step);
    }

}
