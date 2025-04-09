package com.formacion.entities.UserApi;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String address;
    private String city;
    private String state;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

    @Embedded
    private Coordinates coordinates;
}
