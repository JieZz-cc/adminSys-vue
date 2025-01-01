import { defineStore } from "pinia";
import { ref } from "vue";

export const userStore = defineStore('userTokenStore', {
  state: () => ({
    token: '',
    userInfo: {
      username: '',
      id: 0,
      name: '',
      email: null,
      phone: null,
      avatar: null,
      remark: null,
      roleId: 0,
      role: '',
      roleName: '',
      isSuper: 0
    }
  }),
  persist: true,
  getters: {
    getUserInfo(state) {
      return state.userInfo
    },
    getToken(state) {
      return state.token
    }
  },
  actions: {
    setToken(val) {
      this.token = val
    },
    removeToken() {
      this.token = ''
    },
    setUserInfo(user) {
      this.userInfo = user
    }, 
    removeUserInfo() {
      this.userInfo = {}
    }
  }
  
    // const token = ref('')
    // const username = ref('')
    // const setToken = val => {
    //   token.value = val
    // }
    // const removeToken = () => {
    //   token.value = ''
    // }
    // return { token, username, setToken, removeToken }
  }
)