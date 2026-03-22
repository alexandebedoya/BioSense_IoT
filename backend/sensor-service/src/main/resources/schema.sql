CREATE TABLE IF NOT EXISTS sensor (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    value DOUBLE PRECISION,
    unit VARCHAR(50)
);

INSERT INTO sensor (id, name, type, value, unit) VALUES
('1', 'Temperatura', 'Ambiente', 24.5, '°C'),
('2', 'Humedad', 'Ambiente', 60.0, '%'),
('3', 'Calidad Aire', 'AQI', 45.0, 'index')
ON CONFLICT (id) DO NOTHING;