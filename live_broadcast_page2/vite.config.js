import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import inject from "@rollup/plugin-inject";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [

    vue(),
    // inject({
    //   $: "jquery",  // 这里会自动载入 node_modules 中的 jquery
    //   jQuery: "jquery",
    //   "windows.jQuery": "jquery"
    // }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host: 'localhost',//前端服务器IP
    port: 3000,//前端服务器端口号
    open: false,
    proxy: {

      // '/foo': 'wss://localhost:8080/websocket/1',
      '^/backend/': {
        target: 'http://127.0.0.1:8080/',
        // changeOrigin: true,
        rewrite: (path) => path.replace(/^\/backend/, ''),
      },


      "/wsUrl":{
        target: "ws://127.0.0.1:8080", //这里是后台ws访问地址
        changeOrigin: true, //允许跨域设置
        ws: true, //websocket代理设置
        rewrite: (path)=> path.replace(/^\/wsUrl/,""), //拦截路径去除
      },

      "/rtc":'http://192.168.7.130:1985',


      // '^/websocket/': {
      //   target: 'ws://127.0.0.1:8080/websocket',
      //   // changeOrigin: true,
      //   rewrite: (path) => path.replace(/^\/websocket/, ''),
      // },
      // '/foo': 'ws://localhost:8080/websocket/1'
    }
  },

  define: {
    __StreamPushAndPullIp__: JSON.stringify({
      ip:'192.168.7.130'
    }),
  },


})
