// const { app } = require("@/main");


  export default {
    // app.directive('removeAriaHidden', {
      bind(el, binding) {
        console.log(11);
        
        const ariaEls = el.querySelectorAll('.el-popper')
        ariaEls.forEach((item) => {
          item.removeAttribute('aria-hidden')
        })
      }
    // })
  }
