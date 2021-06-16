package com.alevel.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DriverLicence {

    private String[] categories;
    private Instant expireDate;

}
