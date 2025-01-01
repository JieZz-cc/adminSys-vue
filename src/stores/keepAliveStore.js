import { defineStore } from "pinia"

export const usekeepAliveStore = defineStore('usekeepAliveStore', {
  state: () => ({
    keepAliveNames: []
  }),

  getters: {

  },
  // persist: true,
  actions: {
    async addKeepAlive(val) {
      if (this.keepAliveNames.includes(val)) return
      this.keepAliveNames.push(val)
    },
    async removeKeepAlive(val) {
      this.keepAliveNames = this.keepAliveNames.filter(i => i !== val)
    },
    async setKeepAliveNames(aliveArr = []) {
      this.keepAliveNames = aliveArr
    }
  }
})  