package com.wheelson66.tripservice.Entity;

import com.sun.jdi.connect.Transport;
import com.wheelson66.tripservice.Entity.Enums.BudgetLevel;
import com.wheelson66.tripservice.Entity.Enums.Pace;
import com.wheelson66.tripservice.Entity.Enums.TransportMode;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "trip_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TripPreferences extends BaseEntity {
    @Id
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private BudgetLevel budgetLevel;

    @Enumerated(EnumType.STRING)
    private Pace pace;

    @Enumerated(EnumType.STRING)
    private TransportMode transportMode;

    @Column(nullable = false)
    private Integer numberOfAdults;
    private Integer numberOfChildren=0;


}
