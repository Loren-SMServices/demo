package com.formacion.entities.UserApi;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "response_users")
public class User implements Persistable<Long>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Version
	private Integer version = 0; // Campo obligatorio para bloqueo optimista

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private double height;
    private double weight;
    private String eyeColor;
    private String ip;
    private String macAddress;
    private String university;
    private String maidenName;
    private String ein;
    private String ssn;

    @Embedded
    private Hair hair;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "address", column = @Column(name = "user_address")),
        @AttributeOverride(name = "city", column = @Column(name = "user_city")),
        @AttributeOverride(name = "state", column = @Column(name = "user_state")),
        @AttributeOverride(name = "stateCode", column = @Column(name = "user_state_code")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "user_postal_code")),
        @AttributeOverride(name = "country", column = @Column(name = "user_country")),
        @AttributeOverride(name = "coordinates.lat", column = @Column(name = "user_lat")),
        @AttributeOverride(name = "coordinates.lng", column = @Column(name = "user_lng"))
    })
    private Address address;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "department", column = @Column(name = "company_department")),
        @AttributeOverride(name = "name", column = @Column(name = "company_name")),
        @AttributeOverride(name = "title", column = @Column(name = "company_title")),
        @AttributeOverride(name = "address.address", column = @Column(name = "company_address")),
        @AttributeOverride(name = "address.city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "address.state", column = @Column(name = "company_state")),
        @AttributeOverride(name = "address.stateCode", column = @Column(name = "company_state_code")),
        @AttributeOverride(name = "address.postalCode", column = @Column(name = "company_postal_code")),
        @AttributeOverride(name = "address.country", column = @Column(name = "company_country")),
        @AttributeOverride(name = "address.coordinates.lat", column = @Column(name = "company_lat")),
        @AttributeOverride(name = "address.coordinates.lng", column = @Column(name = "company_lng"))
    })
    private Company company;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "cardExpire", column = @Column(name = "bank_card_expire")),
        @AttributeOverride(name = "cardNumber", column = @Column(name = "bank_card_number")),
        @AttributeOverride(name = "cardType", column = @Column(name = "bank_card_type")),
        @AttributeOverride(name = "currency", column = @Column(name = "bank_currency")),
        @AttributeOverride(name = "iban", column = @Column(name = "bank_iban"))
    })
    private Bank bank;

	@Override
	public boolean isNew() {
		return version == null;
	}
}
