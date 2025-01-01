import { defineStore } from "pinia";
import {ref} from 'vue'


export const useGlobalStore = defineStore('global', () => {

    const isCollapse = ref(false)
    const isDark = ref(false)
    const primary = ref('#2254F4')
    const breadcrumb = ref(true)


    const setCollapse = (val) => {
      isCollapse.value = val
    }
    const setPrimary = (val) => {
      primary.value = val
    }
    const setBreadcrumb = (val) => {
      breadcrumb.value = val
    }


    return {isCollapse, isDark, primary, breadcrumb, setCollapse, setPrimary, setBreadcrumb}

  },
  { persist: true  } // 持久化存储
)