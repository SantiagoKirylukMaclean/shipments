package ar.challenge.shipping.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipment_id")
    private int id;

    @Column(name="reference")
    @NotEmpty(message = "*Please provide reference")
    private String reference;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="shipment_id")
    private Set<Parcels> parcels;
}
