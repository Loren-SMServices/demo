package com.formacion.entities.UserApi;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Bank {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;
}