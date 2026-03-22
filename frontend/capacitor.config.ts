import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.biosense.iot',
  appName: 'BioSense IoT',
  webDir: 'out',
  plugins: {
    GoogleAuth: {
      scopes: ['profile', 'email'],
      serverClientId: '669903110693-q703kiablp1rvq509996i5k3dqpbvhml.apps.googleusercontent.com',
      forceCodeForRefreshToken: true,
    },
  },
};

export default config;
