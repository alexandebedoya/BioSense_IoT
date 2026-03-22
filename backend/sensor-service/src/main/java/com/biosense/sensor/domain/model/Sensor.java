package com.biosense.sensor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    private String id;
    private String name;
    private String type;
    private Double value;
    private String unit;
}
