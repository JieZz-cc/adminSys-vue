// 自定义指令集

import removeAriaHidden from "./removeAriaHidden"

const directives = {
  removeAriaHidden
}

export default {
  install(app) {
    Object.keys(directives).forEach(key => {
      app.directive(key, directives[key])
    })
  }
}