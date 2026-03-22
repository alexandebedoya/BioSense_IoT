package com.biosense.sensor.application.service;

import com.biosense.sensor.domain.model.Sensor;
import com.biosense.sensor.domain.ports.in.GetSensorUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class GetSensorUseCaseImpl implements GetSensorUseCase {

    // Simulación de datos (En un caso real se usaría un puerto de salida / repositorio)
    private final List<Sensor> mockSensors = Arrays.asList(
            new Sensor("1", "Temperatura", "Ambiente", 24.5, "°C"),
            new Sensor("2", "Humedad", "Ambiente", 60.0, "%"),
            new Sensor("3", "Calidad Aire", "AQI", 45.0, "index")
    );

    @Override
    public Flux<Sensor> getAll() {
        return Flux.fromIterable(mockSensors);
    }

    @Override
    public Mono<Sensor> getById(String id) {
        return Mono.justOrEmpty(mockSensors.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst());
    }
}
