import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
// import $ from 'jquery';
// window.$ = $;

import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

import './assets/main.css'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import $ from 'jquery';
window.$ = $;

const pinia = createPinia()
const app = createApp(App)

// 小图标

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { fas} from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
library.add(fas)


// 浏览器测试vue用
app.config.devtools = true

app.use(ElementPlus)
app.use(pinia)
app.use(router)
app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app')
