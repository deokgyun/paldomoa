package com.paldomoa.common.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String street;
    private String city;
    private String zipcode;
}
