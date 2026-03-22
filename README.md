# BioSense IoT Project

Proyecto de monitoreo IoT organizado con una arquitectura de microservicios y frontend desacoplado.

## Estructura del Proyecto

```text
/BioSense_IoT
├── frontend/             # Aplicación Next.js (React 19)
├── backend/              # Microservicios en Spring Boot (Java 17)
│   └── sensor-service/   # Microservicio de Sensores (Arquitectura Limpia + WebFlux)
├── docker-compose.yml    # Infraestructura de contenedores (Base de datos, etc.)
└── README.md
```

## Requisitos
- **Java 17**
- **Node.js 18+**
- **pnpm** (recomendado para frontend)
- **Docker & Docker Compose**

## Ejecución

### Frontend
```bash
cd frontend
pnpm install
pnpm dev
```

### Backend (Sensor Service)
```bash
cd backend/sensor-service
./mvnw spring-boot:run
```

O usando Docker Compose para toda la infraestructura:
```bash
docker-compose up -d
```

## Arquitectura del Backend
El backend sigue los principios de **Arquitectura Limpia (Hexagonal)**:
- **Domain**: Contiene la lógica de negocio y definiciones de modelos/puertos. Sin dependencias externas.
- **Application**: Implementa los casos de uso orquestando el dominio.
- **Infrastructure**: Implementaciones técnicas (REST, R2DBC, Mensajería).
