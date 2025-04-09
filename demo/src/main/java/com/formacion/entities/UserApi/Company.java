package com.formacion.entities.UserApi;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Company {
    private String department;
    private String name;
    private String title;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "address", column = @Column(name = "company_address")),
        @AttributeOverride(name = "city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "state", column = @Column(name = "company_state")),
        @AttributeOverride(name = "stateCode", column = @Column(name = "company_state_code")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "company_postal_code")),
        @AttributeOverride(name = "country", column = @Column(name = "company_country")),
        @AttributeOverride(name = "address.coordinates.lat", column = @Column(name = "company_lat")),
        @AttributeOverride(name = "address.coordinates.lng", column = @Column(name = "company_lng"))
    })
    private Address address;
}

