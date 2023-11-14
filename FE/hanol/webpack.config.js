// webpack.config.js 또는 해당 빌드 도구 설정 파일
import { WorkboxPlugin } from 'workbox-webpack-plugin';

module.exports = {
  // ... 다른 설정 ...

  plugins: [
    // Workbox 플러그인 추가
    new WorkboxPlugin.GenerateSW({
      // 모든 리소스를 캐싱하지 않음
      // 모든 요청을 네트워크로 전달
      // 캐시 관리가 필요하지 않은 경우에 사용
      skipWaiting: true,
      clientsClaim: true,
    }),
  ],
};
