package com.formacion.entities.UserApi;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Hair {
    private String color;
    private String type;
}