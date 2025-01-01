// import './assets/main.css'

import './styles/common.scss'
import './styles/main.scss'
import './styles/dark.scss'
import './styles/var.scss'
import './styles/iconfont.scss'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'
import 'element-plus/es/components/tree/style/css'

import {invokeApi} from '@/api'
// import directive from './directive';

// 中文语言包
import locale from 'element-plus/dist/locale/zh-cn'

import piniaPluginPersist from 'pinia-plugin-persistedstate'     // 持久化存储
import { zhCn } from 'element-plus/es/locales.mjs'

export const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersist)
app.use(pinia)

// app.use(directive)
app.use(router)
app.use(ElementPlus,{ locale:zhCn })
app.use(ElementPlus)


app.config.globalProperties.invokeApi = invokeApi

app.mount('#app')

