package org.pyv.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "marker_id", nullable = false)
    private Marker marker;
}
