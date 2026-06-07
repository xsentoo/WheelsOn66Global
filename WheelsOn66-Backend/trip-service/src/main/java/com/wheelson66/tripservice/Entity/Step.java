package com.wheelson66.tripservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.geolatte.geom.Point;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Table(name = "steps")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Step extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id" , nullable = false)
    private Trip trip;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(length = 255)
    private String locationName;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point geom;

    private String placeId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String cachedPlaceData;

    private Instant arrivalTimestamp;
    private Instant departureTimestamp;

}
