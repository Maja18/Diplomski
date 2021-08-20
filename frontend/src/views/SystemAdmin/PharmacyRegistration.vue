<template>
<div id="pharmacyRregistration">
    <div class="homepage_style ">
             <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
                     <b-button pill variant="info" style="margin-left:30px" class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profil</b-button>
                      <b-dropdown style="height:45px;color:black" text="Registracija" variant="info" pill >
                         <b-dropdown-item href="/pharmacyRegistration">Apoteke</b-dropdown-item>
                         <b-dropdown-item href="/dermatologyRegistration">Dermatologa</b-dropdown-item>
                         <b-dropdown-item href="/dermatologyRegistration">Farmaceuta</b-dropdown-item>
                         <b-dropdown-item href="/supplierRegistration">Dobavljača</b-dropdown-item>
                    </b-dropdown>
                    <b-button pill variant="info" class = "btn btn-info btn-lg space_style" v-on:click = "showDrugManipulation">Lekovi</b-button>
                    <b-button pill variant="info" class = "btn btn-info btn-lg space_style" v-on:click = "showLoalty">Loalty klub</b-button>
                    <b-button pill variant="info" class = "btn btn-info btn-lg space_style" v-on:click = "showComplaints">Žalbe</b-button>
            </span>
             <span  style="float:right;margin:15px">
             <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "logOut">
                     <b-icon icon="power"   aria-hidden="true"></b-icon> Odjavi se
            </b-button>
            </span>
        </div>
    <div class="vue-tempalte" style="margin-top:-10px">
            <h3 style="margin-top:20px">Registracija apoteke</h3>
            <div class="form-group" style="margin-top:10px">
                <label style="color:white">Ime</label>
                 <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="person"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="Ime" v-model="name" required></b-form-input>
                        </b-input-group>
                <label style="color:white">Zemlja</label>
                         <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="geo"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="Zemlja" v-model="country" required></b-form-input>
                        </b-input-group>
                 <label style="color:white">Grad</label>
                       <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="building"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="Grad" v-model="city" required></b-form-input>
                        </b-input-group>
               <label style="color:white">Ulica</label>
                       <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="geo-alt"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="Ulica" v-model="street" required></b-form-input>
                        </b-input-group>
                 <label style="color:white">Broj ulice</label>
                         <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="geo-alt"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="number" placeholder="1" v-model="streetNumber" required></b-form-input>
                        </b-input-group>
                <label style="color:white">Opis</label>
                <b-input-group size="lg">
                            <b-input-group-prepend is-text>
                              <b-icon icon="file-medical"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="Opis" v-model="street" required></b-form-input>
                        </b-input-group>
            </div>
               <b-button pill style="color:white; margin-top:70px" type="submit" class="button" v-on:click = "registerPharmacy">Registruj</b-button>
        
    </div>
</div>
</template>

<script>
export default {
    name: 'PharmacyRegistration',
    data(){
        return{
            city:'',
            street:'',
            streetNumber:'',
            country:'',
            name:'',
            description:'',
        }

    },
     methods:{
        validLettersName: function (name) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(name);
       },
       validLettersDescription: function (description) {
        var res = /^[a-zA-Z]+$/;
        return res.test(description);
       },
       validLettersCountry: function (country) {
        var res = /^[a-zA-Z]+$/;
        return res.test(country);
       },
       validLettersCity: function (city) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(city);
       },
       validLettersStreet: function (street) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(street);
       },
        showHomePage: function(){
           window.location.href = "/homePage";
        },
         showProfile: function(){
           window.location.href = "/profileData";
        },
        showDermatologyRegistration: function(){
           window.location.href = "/dermatologyRegistration";
        },
        showPharmacyRegistration: function(){
           window.location.href = "/pharmacyRegistration";
        },
         showLoalty: function(){
           window.location.href = "/loaltyProgram";
        },
          showSupplierRegistration: function(){
           window.location.href = "/supplierRegistration";
        },
         showSystemAdminRegistration: function(){
           window.location.href = "/systemAdminRegistration";
        },
         showPharmacyAdminRegistration: function(){
           window.location.href = "/pharmacyAdminRegistration";
        },
        showDrugManipulation: function(){
           window.location.href = "/addingDrug";
        },
        showComplaints: function(){
           window.location.href = "/complaints";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        registerPharmacy : function(){
          const addressInfo ={
              town: this.city,
              street: this.street,
              number: this.streetNumber,
              country: this.country
            }
            const pharmacyInfo ={
              name : this.name,
              addressDTO : addressInfo,
              description: this.description
            }
            
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
             if(!this.validLettersName(this.name)){
                alert("Please enter valid name!")
                return;
            }
             if(!this.validLettersCountry(this.country)){
                alert("Please enter valid conutry!")
                return;
            }
             if(!this.validLettersCity(this.city)){
                alert("Please enter valid city!")
                return;
            }
            if(!this.validLettersStreet(this.street)){
                alert("Please enter valid street!")
                return;
            }
            if(this.streetNumber ==""){
                alert("Please enter streetNumber!")
                return;
            }
            if(this.streetNumber < 0){
                alert("Please enter valid streetNumber!")
                return;
            }
             if(!this.validLettersDescription(this.description)){
                alert("Please enter valid description!")
                return;
            }
            
            this.axios.post('/pharmacy/registerPharmacy',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }
                        })
                .then(response => {
                       alert("Pharmacy is successfully registred!");
                        console.log(response.data);
                })
                .catch(res => {
                    alert("Greska " + res.response.data.message);
                 });    
       }
     }
    
}
</script>

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f6f9fa!important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
    }

.form-group{
  position: relative;
  z-index: 1;
  background:  #174452;
  max-width: 400px;
  margin: 0 auto 100px;
  padding: 65px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  
    }
.button {
   background:  #174452;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(25, 40, 90); 
   padding: 10px 20px;
   position: absolute;
   top: 95%;
   right: 40%;
   width: 20%;
   height: 7%;
   
}

.homepage_style{
        background: #17a2b8; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 73px;
    }

    .space_style{
        margin-right:5px
    }
</style>