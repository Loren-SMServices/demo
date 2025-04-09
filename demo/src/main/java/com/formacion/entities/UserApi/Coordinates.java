package com.formacion.entities.UserApi;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Coordinates {
    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;
}
