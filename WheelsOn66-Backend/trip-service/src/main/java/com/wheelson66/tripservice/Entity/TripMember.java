package com.wheelson66.tripservice.Entity;

import com.wheelson66.tripservice.Entity.Enums.JoinStatus;
import com.wheelson66.tripservice.Entity.Enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trip_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class TripMember extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(nullable = false)
    private Long userId; // Venant du User-Service

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role = MemberRole.VIEWER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JoinStatus joinStatus = JoinStatus.PENDING;
}
