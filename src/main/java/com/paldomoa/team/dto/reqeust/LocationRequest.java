package com.paldomoa.team.dto.reqeust;

import com.paldomoa.common.domain.Address;
import com.paldomoa.common.domain.Location;

public record LocationRequest(
    double latitude,
    double longitude,
    String name,
    String street,
    String city,
    String zipcode
) {

    public Location createLocation(LocationRequest request) {
        return Location.builder()
            .latitude(request.latitude())
            .longitude(request.longitude())
            .address(new Address(request.street(), request.city(), request.zipcode()))
            .name(request.name())
            .build();
    }
}
