package com.biosense.sensor.infrastructure.adapter.out.persistence;

import com.biosense.sensor.domain.model.Sensor;
import com.biosense.sensor.domain.ports.out.SensorRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SensorRepositoryImpl implements SensorRepository {

    private final DatabaseClient databaseClient;

    public SensorRepositoryImpl(ConnectionFactory connectionFactory) {
        this.databaseClient = DatabaseClient.create(connectionFactory);
    }

    @Override
    public Flux<Sensor> findAll() {
        return databaseClient.sql("SELECT id, name, type, value, unit FROM sensor")
                .map((row, metadata) -> Sensor.builder()
                        .id(row.get("id", String.class))
                        .name(row.get("name", String.class))
                        .type(row.get("type", String.class))
                        .value(row.get("value", Double.class))
                        .unit(row.get("unit", String.class))
                        .build())
                .all();
    }

    @Override
    public Mono<Sensor> findById(String id) {
        return databaseClient.sql("SELECT id, name, type, value, unit FROM sensor WHERE id = :id")
                .bind("id", id)
                .map((row, metadata) -> Sensor.builder()
                        .id(row.get("id", String.class))
                        .name(row.get("name", String.class))
                        .type(row.get("type", String.class))
                        .value(row.get("value", Double.class))
                        .unit(row.get("unit", String.class))
                        .build())
                .one();
    }

    @Override
    public Mono<Sensor> save(Sensor sensor) {
        return databaseClient
                .sql("INSERT INTO sensor (id, name, type, value, unit) VALUES (:id, :name, :type, :value, :unit)")
                .bind("id", sensor.getId())
                .bind("name", sensor.getName())
                .bind("type", sensor.getType())
                .bind("value", sensor.getValue())
                .bind("unit", sensor.getUnit())
                .fetch()
                .rowsUpdated()
                .thenReturn(sensor);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return databaseClient.sql("DELETE FROM sensor WHERE id = :id")
                .bind("id", id)
                .fetch()
                .rowsUpdated()
                .then();
    }
}