package com.biosense.sensor.infrastructure.adapter.in.web;

import com.biosense.sensor.domain.model.Sensor;
import com.biosense.sensor.domain.ports.in.GetSensorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final GetSensorUseCase getSensorUseCase;

    @GetMapping
    public Flux<Sensor> getAll() {
        return getSensorUseCase.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Sensor> getById(@PathVariable String id) {
        return getSensorUseCase.getById(id);
    }
}
