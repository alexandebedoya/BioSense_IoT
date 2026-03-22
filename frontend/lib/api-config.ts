export const API_CONFIG = {
  // URLs reales de tu despliegue en Railway
  // Nota: Railway redirige automáticamente el tráfico del dominio al puerto 8080 internamente.
  AUTH_URL: "https://biosenseiot-production.up.railway.app/api/auth",
  SENSOR_URL: "https://sensor-service-production.up.railway.app/api/sensors", // Ajusta esta cuando despliegues el Sensor Service
  
  // Para pruebas en local
  LOCAL_AUTH_URL: "http://localhost:8080/api/auth",
};

export const getAuthUrl = () => {
  // En producción (App móvil Android/iOS) siempre usará la URL de Railway
  // En desarrollo local usará la URL de localhost
  return process.env.NODE_ENV === 'development' ? API_CONFIG.LOCAL_AUTH_URL : API_CONFIG.AUTH_URL;
};
