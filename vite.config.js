import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'


// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    // element-ui组件按需导入插件
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    })
  ],
  base: './',
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        api: 'modern-compiler'
      }
    }
  },
  server: {
    proxy: {  // 代理
      '/api': {   // 包含api的请求
        target: 'http://localhost:8080',  // 后端服务的地址
        // target: 'http://175.178.159.253:3501/api/',  // 后端服务的地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '') // 把/api 换成空字符串
      }
    },
    // hmr:{
    //   port: 5555
    // }
  },
  build: {
    outDir: 'dist'
  }
})
