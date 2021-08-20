import Vue from 'vue'
import App from './App.vue'
import { BootstrapVue, BootstrapVueIcons  } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import moment from 'moment'

Vue.use(VueAxios, axios)
Vue.use(BootstrapVue)
Vue.use(VueRouter)
Vue.use(BootstrapVueIcons)

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router';

Vue.prototype.$axios = axios;
Vue.config.productionTip = false

const baseURL = 'http://localhost:8081/api';
if (typeof baseURL !== 'undefined') {
  Vue.axios.defaults.baseURL = baseURL;
}

Vue.filter('formatDate', function(value) {
  if (value) {
      return moment(String(value)).format('MM/DD/YYYY hh:mm')
  }
});


Vue.filter('formatDate', function(value) {
  if (value) {
      return moment(String(value)).format('YYYY-MM-DD[T]HH:mm:ss.SSS[Z]');
  }
});

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
