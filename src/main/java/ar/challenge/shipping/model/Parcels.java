package ar.challenge.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parcels")
public class Parcels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parcels_id")
    private int id;

    @Column(name="weight")
    @NotNull(message = "*Please provide weigth")
    private int weight;

    @Column(name="width")
    @NotNull(message = "*Please provide width")
    private int width;

    @Column(name="height")
    @NotNull(message = "*Please provide height")
    private int height;

    @Column(name="lenght")
    @NotNull(message = "*Please provide lenght")
    private int lenght;
}
