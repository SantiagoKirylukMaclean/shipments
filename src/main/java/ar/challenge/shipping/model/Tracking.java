package ar.challenge.shipping.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tracking")
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tracking_id")
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "parcels")
    private Integer parcels;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "reference")
    private String reference;

}



