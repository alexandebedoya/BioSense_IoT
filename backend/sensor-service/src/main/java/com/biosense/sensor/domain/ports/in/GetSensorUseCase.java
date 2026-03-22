package com.biosense.sensor.domain.ports.in;

import com.biosense.sensor.domain.model.Sensor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetSensorUseCase {
    Flux<Sensor> getAll();
    Mono<Sensor> getById(String id);
}
