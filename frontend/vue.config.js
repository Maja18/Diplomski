module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],

  "runtimeCompiler": true,

  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8085',
        ws: true,
       
        changeOrigin: true
      }
    }
  },

  outputDir: '../../../target/front/public',

  transpileDependencies: [
    'vuetify'
  ]
}
  
