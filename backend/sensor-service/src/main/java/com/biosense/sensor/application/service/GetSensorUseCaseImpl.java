package com.biosense.sensor.application.service;

import com.biosense.sensor.domain.model.Sensor;
import com.biosense.sensor.domain.ports.in.GetSensorUseCase;
import com.biosense.sensor.domain.ports.out.SensorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetSensorUseCaseImpl implements GetSensorUseCase {

    private final SensorRepository sensorRepository;

    public GetSensorUseCaseImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Flux<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    @Override
    public Mono<Sensor> getById(String id) {
        return sensorRepository.findById(id);
    }
}
