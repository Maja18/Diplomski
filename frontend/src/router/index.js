import Vue from 'vue'
import VueRouter from 'vue-router'
import BootstrapVue from "bootstrap-vue";
import { ValidationObserver, ValidationProvider, extend } from 'vee-validate';
import * as rules from 'vee-validate/dist/rules';
import Home from '../views/Home.vue'
import Login from '../views/SystemAdmin/Login.vue'
import DermatologyRegistration from '../views/SystemAdmin/DermatologyRegistration.vue'
import HomePage from '../views/SystemAdmin/HomePage.vue'
import ProfileData from '../views/SystemAdmin/ProfileData.vue'
import PharmacyRegistration from '../views/SystemAdmin/PharmacyRegistration.vue'
import SupplierRegistration from '../views/SystemAdmin/SupplierRegistration.vue'
import DermatologistHomepage from '../views/dermatologistView/DermatologistHomepage.vue'
import DermatologistProfile from '../views/dermatologistView/DermatologistProfile.vue'
import SystemAdminRegistration from '../views/SystemAdmin/SystemAdminRegistration.vue'
import Registration from '../views/SystemAdmin/Registration.vue'
import PharmacyAdminRegistration from '../views/SystemAdmin/PharmacyAdminRegistration.vue'
import DermatologistAbsenceRequest from '../views/dermatologistView/DermatologistAbsenceRequest.vue'
import DermatologistExamination from '../views/dermatologistView/DermatologistExamination.vue'
import DermatologistNewExamination from '../views/dermatologistView/DermatologistNewExamination.vue'
import DermatologistPatients from '../views/dermatologistView/DermatologistPatients.vue'
import DermatologistWorkCalendar from '../views/dermatologistView/DermatologistWorkCalendar.vue'
import PharmacistHomepage from '../views/pharmacistView/PharmacistHomepage.vue'
import PharmacistAbsenceRequest from '../views/pharmacistView/PharmacistAbsenceRequest.vue'
import PharmacistConsalting from '../views/pharmacistView/PharmacistConsalting.vue'
import PharmacistNewConsalting from '../views/pharmacistView/PharmacistNewConsalting.vue'
import PharmacistPatients from '../views/pharmacistView/PharmacistPatients.vue'
import PharmacistProfile from '../views/pharmacistView/PharmacistProfile.vue'
import PharmacistWorkCalendar from '../views/pharmacistView/PharmacistWorkCalendar.vue'
import AddingDrug from '../views/SystemAdmin/AddingDrug.vue'
import ProfileDataSupplier from '../views/SupplierView/ProfileDataSupplier.vue'
import Offers from '../views/SupplierView/Offers.vue'
import Drugs from '../views/SupplierView/Drugs.vue'
import GiveOffers from '../views/SupplierView/GiveOffers.vue'
import Promotions from '../views/PharmacyView/Promotions.vue'
import Profile from '../views/PatientView/Profile.vue'
import Complaint from '../views/PatientView/Complaint.vue'
import Complaints from '../views/SystemAdmin/Complaints.vue'
import ShowDrugs from '../views/PharmacyView/ShowDrugs.vue'
import LoaltyProgram from '../views/SystemAdmin/LoaltyProgram.vue'
import EPrescription from '../views/PatientView/EPrescription.vue'
import PharmacistDispensingDrugs from '../views/pharmacistView/PharmacistDispensingDrugs.vue'
import BodyCare from '../views/PharmacyView/BodyCare.vue'
import Vitamins from '../views/PharmacyView/Vitamins.vue'
import DrugAdvices from '../views/PharmacyView/DrugAdvices.vue'
import ProfileInfo from '../views/PatientView/ProfileInfo.vue'

Vue.use(BootstrapVue)
Vue.use(VueRouter)

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Object.keys(rules).forEach(rule => {
  extend(rule, rules[rule]);
});

Vue.component('ValidationObserver', ValidationObserver);
Vue.component('ValidationProvider', ValidationProvider);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {

    path: '/homePage',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/systemAdminRegistration',
    name: 'SystemAdminRegistration',
    component: SystemAdminRegistration
  },
  {
    path: '/pharmacyAdminRegistration',
    name: 'PharmacyAdminRegistration',
    component: PharmacyAdminRegistration
  },
  {
    path: '/dermatologyRegistration',
    name: 'DermatologyRegistration',
    component: DermatologyRegistration
  },
  {
    path: '/pharmacyRegistration',
    name: 'PharmacyRegistration',
    component: PharmacyRegistration
  },
  {
    path: '/supplierRegistration',
    name: 'SupplierRegistration',
    component: SupplierRegistration
  },
  {
    path: '/profileData',
    name: 'ProfileData',
    component: ProfileData
  },
  {
    path: '/dermatologistHomepage',
    name: 'DermatologistHomepage',
    component: DermatologistHomepage
  },
  {
    path: '/dermatologistProfile',
    name: 'DermatologistProfile',
    component: DermatologistProfile
  },
  {
    path: '/dermatologistAbsenceRequest',
    name: 'DermatologistAbsenceRequest',
    component: DermatologistAbsenceRequest
  },
  {
    path: '/dermatologistExamination/:selectedExamination',
    name: 'DermatologistExamination',
    component: DermatologistExamination
  },
  {
    path: '/dermatologistNewExamination',
    name: 'DermatologistNewExamination',
    component: DermatologistNewExamination
  },
  {
    path: '/dermatologistPatients',
    name: 'DermatologistPatients',
    component: DermatologistPatients
  },
  {
    path: '/dermatologistWorkCalendar',
    name: 'DermatologistWorkCalendar',
    component: DermatologistWorkCalendar
  },
  {
    path: '/pharmacistHomepage',
    name: 'PharmacistHomepage',
    component: PharmacistHomepage
  },
  {
    path: '/pharmacistAbsenceRequest',
    name: 'PharmacistAbsenceRequest',
    component: PharmacistAbsenceRequest
  },
  {
    path: '/pharmacistConsalting/:selectedCounseling',
    name: 'PharmacistConsalting',
    component: PharmacistConsalting
  },
  {
    path: '/pharmacistNewConsalting',
    name: 'PharmacistNewConsalting',
    component: PharmacistNewConsalting
  },
  {
    path: '/pharmacistPatients',
    name: 'PharmacistPatients',
    component: PharmacistPatients
  },
  {
    path: '/pharmacistProfile',
    name: 'PharmacistProfile',
    component: PharmacistProfile
  },
  {
    path: '/pharmacistWorkCalendar',
    name: 'PharmacistWorkCalendar',
    component: PharmacistWorkCalendar
  },
  {
    path: '/pharmacistDispensingDrugs',
    name: 'PharmacistDispensingDrugs',
    component: PharmacistDispensingDrugs
  },
  {
    path: '/addingDrug',
    name: 'AddingDrug',
    component: AddingDrug
  },
  {
    path: '/profileDataSupplier',
    name: 'ProfileDataSupplier',
    component: ProfileDataSupplier
  },
  {
    path: '/offers',
    name: 'Offers',
    component: Offers
  },
  {
    path: '/drugs',
    name: 'Drugs',
    component: Drugs
  },
  {
    path: '/giveOffers',
    name: 'GiveOffers',
    component: GiveOffers
  },
  {
    path: '/promotions',
    name: 'Promotions',
    component: Promotions
  },
  {
    path: '/patientProfile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/complaint',
    name: 'Complaint',
    component: Complaint
  },
  {
    path: '/complaints',
    name: 'Complaints',
    component: Complaints
  },
  {
    path: '/showDrugs',
    name: 'ShowDrugs',
    component: ShowDrugs
  },
  {
    path: '/loaltyProgram',
    name: 'LoaltyProgram',
    component: LoaltyProgram
  },
  {
    path: '/ePrescription',
    name: 'EPrescription',
    component: EPrescription
  },
  {
    path: '/bodyCare',
    name: 'BodyCare',
    component: BodyCare
  },
  {
    path: '/vitamins',
    name: 'Vitamins',
    component: Vitamins
  },
  {
    path: '/drugAdvices',
    name: 'DrugAdvices',
    component: DrugAdvices
  },
  {
    path: '/profileInfo',
    name: 'ProfileInfo',
    component: ProfileInfo
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router


