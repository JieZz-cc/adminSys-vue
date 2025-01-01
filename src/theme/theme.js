import { getDarkColor, getLightColor } from "@/utils/color"
import { storeToRefs } from 'pinia' // 将store里的数据变为响应式
import {useGlobalStore} from '@/stores/modules/global'

export const changeTheme = () => {
  const globalStore = useGlobalStore()
  const { isDark, primary } = storeToRefs(globalStore)
  

  // 切换暗黑模式 ==> 同时修改主题颜色、侧边栏、头部颜色
  const switchDark = () => {
    
    const html = document.documentElement
    if (isDark.value) html.setAttribute('class', 'dark')
    else html.setAttribute('class', '')
    changePrimary(primary.value)
  }

  const changePrimary = (val) => {
    if (!val) val = '#2254F4'
    document.documentElement.style.setProperty('--el-color-primary', val)
    document.documentElement.style.setProperty('--el-color-primary-dark-2', 
      isDark.value ? `${getLightColor(val, 0.2)}` : `${getDarkColor(val, 0.3)}`)

    for (let i = 0; i <= 9; i++) {
      const primaryColor = isDark.value ? `${getDarkColor(val, i / 10)}` : `${getLightColor(val, i / 10)}`
      document.documentElement.style.setProperty(`--el-color-primary-light-${i}`, primaryColor)
    }
    globalStore.setPrimary(val)
  }
  
  const initTheme = () => {
    switchDark()
    changePrimary(primary.value)
  }

  return { switchDark, changePrimary, initTheme }
}