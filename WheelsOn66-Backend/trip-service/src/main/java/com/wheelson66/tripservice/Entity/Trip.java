package com.wheelson66.tripservice.Entity;

import ch.qos.logback.core.status.Status;
import com.wheelson66.tripservice.Entity.Enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.geolatte.geom.LineString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Trip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(unique = true , length  = 10)
    private String tripHash;

    @Version
    private Long version;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status = TripStatus.DRAFT;

    @Column(columnDefinition = "geometry(LineString,4326)")
    private LineString routeLine;

    private Integer totalDistanceKm;
    private Integer totalDurationMinutes;

    @Column(nullable = false)
    private boolean isMysteryEligible = false;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private TripPreferences preferences;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC") // Trie automatiquement les étapes
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripMember> members = new ArrayList<>();


}
