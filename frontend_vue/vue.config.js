const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer:{
    port:10000,
    proxy: {                    //Proxy Configuration
      '/api': {               //Set Interceptor, Slash + Interceptor Name
        target: 'http://localhost:9090',  //Proxy Target URL, replace http://localhost:9090 with /api
        changeOrigin: true,                 //Whether to enable same-origin, set to true to achieve cross-origin
        pathRewrite: {                      //path rewrite
          '/api': ''                      //omit words
        }
      }
    }
  }
}