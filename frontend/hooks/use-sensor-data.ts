'use client'

import useSWR from 'swr'
import { SensorData } from '@/lib/types'
import { API_CONFIG } from '@/lib/api-config'

const fetcher = (url: string) => fetch(url).then(res => {
  if (!res.ok) throw new Error('Error al obtener datos')
  return res.json()
})

export function useSensorData() {
  // Por ahora apuntamos al GET general, luego lo filtraremos por dispositivo/usuario
  const { data, error, isLoading, mutate } = useSWR<SensorData[]>(
    API_CONFIG.SENSOR_URL,
    fetcher,
    {
      refreshInterval: 5000, // Cada 5 segundos en producción para no saturar
      revalidateOnFocus: true,
    }
  )

  // Adaptamos el array de sensores al objeto único que espera el frontend actual
  const latestData: SensorData | undefined = data && data.length > 0 ? {
    mq4: data[0].mq4_value || 0,
    mq7: data[0].mq7_value || 0,
    mq135: data[0].mq135_value || 0,
    nivel: 'NORMAL', // Esto lo calculará la IA o el backend después
    timestamp: new Date().toISOString()
  } : undefined;

  return {
    data: latestData,
    isLoading,
    isError: !!error,
    error,
    refresh: mutate
  }
}
