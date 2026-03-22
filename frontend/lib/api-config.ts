export const API_CONFIG = {
  // URLs reales de tu despliegue en Railway
  AUTH_URL: "https://biosenseiot-production.up.railway.app/api/auth",
  SENSOR_URL: "https://biosenseiot-production.up.railway.app/api/sensors",
  
  // Para pruebas en local
  LOCAL_AUTH_URL: "http://localhost:8082/api/auth",
};

export const getAuthUrl = () => {
  // En producción (App móvil) siempre usará la URL de Railway
  return process.env.NODE_ENV === 'development' ? API_CONFIG.LOCAL_AUTH_URL : API_CONFIG.AUTH_URL;
};
