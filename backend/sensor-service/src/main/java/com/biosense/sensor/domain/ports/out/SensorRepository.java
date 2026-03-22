package com.biosense.sensor.domain.ports.out;

import com.biosense.sensor.domain.model.Sensor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SensorRepository {
    Flux<Sensor> findAll();

    Mono<Sensor> findById(String id);

    Mono<Sensor> save(Sensor sensor);

    Mono<Void> deleteById(String id);
}