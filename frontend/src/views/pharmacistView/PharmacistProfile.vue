<template>
    <div id="pharmacistProfile">
        <!-- Navigation bar -->
        <div class="homepage_style "  >
            <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 50px; height: 50px; margin-right:10px;" src="@/images/natural-medicine.png">
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showHomepage">Home</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profile</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">My patients</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Work calendar</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Create a vacation</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showConsalting">Consaltings</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showNewConsalting">Schedule new consalting</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showDispensingDrugs">Dispensing drugs</button>
            </span>
            <span  style="float:right;margin:15px">
                <button class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span>
        </div>
        <!-- Navigation bar bg-white mt-5 mb-5-->
        <div class="container bg-white   mt-5 mb-5">
   
            <div class="card"  >
                <div class="profile-img">
                    <img class="img-responsive" src="@/images/nature.jpg" style=" height:150px;" width="100%" />
                    <img class="img-circle img-responsive rounded-circle"  src="@/images/dermatologist.png" style="width:120px; height:120px;"  />
                </div>
                <div class=" d-inline-block " style=" height:100%; background-color: #ced2d3;">
                    <h4 style = "position:left; left:60px; top:2px; background-color:#ebf0fa;"><b>dr {{pharmacist.name}}  {{pharmacist.surname}} </b></h4>
                    <div class="p-3 py-5 ">
                    <div class="col-md-8 ">
                    <div class="card mb-3 " style = "position:right; left:90px; top:2px; background-color:#ebf0fa;">
                    <div class="card-body" style="background-color: #ebf0fa;">
                        <h4 class ="text-justify top-buffer"> Name:   {{pharmacist.name}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Surname:   {{pharmacist.surname}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Email:   {{pharmacist.email}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Contact:   {{pharmacist.contact}}</h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Address:   {{pharmacist.address.street}}, {{pharmacist.address.number}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> City:  {{pharmacist.address.town}}, {{pharmacist.address.country}} </h4>
                        
                </div>
              </div>
            </div>
                        
                         <!-- <div class="mt-5 text-center top-buffer"><button class="btn btn-info btn-lg space_style" style="background-color:#003d66;"  v-on:click = "editProfile">Edit profile</button></div>-->  
                        <!-- Modal --> 
                        <div class="mt-5 text-center top-buffer">
                            <b-button  class="btn btn-info btn-lg space_style" style="background-color:#003d66;" v-b-modal.modal-2>Edit password</b-button>
                            <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#003d66;" v-b-modal.modal-1>Edit profile info</b-button>
                            <b-modal ref="modal-ref" id="modal-1" title="Edit profile info" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer"> Name:
                                        <b-form-input v-model="pharmacist.name" label="First Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Last Name:
                                        <b-form-input v-model="pharmacist.surname" label="Last Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Email:
                                        <b-form-input v-model="pharmacist.email" label="Email" disabled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Contact:
                                        <b-form-input v-model="pharmacist.contact" label="Contact"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Street:
                                        <b-form-input v-model="pharmacist.address.street" label="Street" filled placeholder="Enter your street name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Street Number:
                                        <b-form-input v-model="pharmacist.address.number" label="Street Number" filled placeholder="Enter your street number"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> City:
                                        <b-form-input v-model="pharmacist.address.town" label="City" filled placeholder="Enter the city name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Country:
                                        <b-form-input v-model="pharmacist.address.country" label="Country" filled placeholder="Enter the country name"></b-form-input>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#003d66; width:5cm;" v-on:click = "cancel">Cancel</b-button>
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#003d66; width:5cm;" v-on:click = "update">Update</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                            <b-modal ref="modal-ref2" id="modal-2" title="Edit profile info" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Current Password</label>
                                        <VuePassword
                                            v-model="pharmacist.currentPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                            :badge="false" 
                                            :toggle="true"
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">New Password:</label>
                                        <VuePassword
                                            v-model="pharmacist.newPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Repeat New Password:</label>
                                        <VuePassword
                                            v-model="pharmacist.repeatNewPassword"
                                            id="password1"
                                            placeholder="Enter your current password"
                                        />
                                        </div>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#003d66; width:5cm;" v-on:click = "cancelPassword">Cancel</b-button>
                                        <b-button class="btn btn-info btn-lg space_style" style="background-color:#003d66; width:5cm;" v-on:click = "updatePassword">Update</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</template>

<script>

import VuePassword from 'vue-password'
export default {
name: 'PharmacistProfile',
 components: {
    VuePassword,
  },
data() {
    return {
        pharmacist : "",
        name: "",
        surname: "",
        email: "",
        address : "",
        password : "",
        currentPassword : "",
        newPassword : "",
        repeatNewPassword : ""
    }
  },
  mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/profile',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.pharmacist = response.data;
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
    
   },
    methods:{
        toggle () {
        this.show = !this.show
        },
       showHomepage: function(){
           window.location.href = "/pharmacistHomepage";
        },
        showProfile: function(){
           window.location.href = "/pharmacistProfile";
        },
        showPatients: function(){
            window.location.href = "/pharmacistPatients";
        },
        showWorkCalendar: function(){
            window.location.href = "/pharmacistWorkCalendar";
        },
        showAbsenceRequest: function(){
            window.location.href = "/pharmacistAbsenceRequest";
        },
        showConsalting: function(){
            window.location.href = "/pharmacistConsalting/-1";
        },
        showNewConsalting: function(){
            window.location.href = "/pharmacistNewConsalting";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        cancel() {
            this.$refs['modal-ref'].hide();
        },
        cancelPassword() {
            this.$refs['modal-ref2'].hide();
        },
        showDispensingDrugs : function(){
            window.location.href = "/pharmacistDispensingDrugs";
        },
        validEmail: function (email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        },
        validPhone: function (phone) {
            var res = /^\d{10}$/;
            return res.test(phone);
        },
        validLettersName: function (name) {
            var res = /^[a-zA-Z]+$/;
            return res.test(name);
        },
        validLettersSurname: function (surname) {
            var res = /^[a-zA-Z]+$/;
            return res.test(surname);
        },
        validLettersCountry: function (country) {
            var res = /^[a-zA-Z]+$/;
            return res.test(country);
        },
        validLettersCity: function (city) {
            var res = /^[a-zA-Z]+$/;
            return res.test(city);
        },
        validLettersStreet: function (street) {
            var res = /^[a-zA-Z]+$/;
            return res.test(street);
        },
        update : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const addressInfo = {
                town: this.pharmacist.address.town,
                street: this.pharmacist.address.street,
                number:this.pharmacist.address.number,
                country: this.pharmacist.address.country
            };
            const pharmacistInfo = {
                name: this.pharmacist.name,
                surname : this.pharmacist.surname,
                email:this.pharmacist.email,
                address: addressInfo,
                contact: this.pharmacist.contact
           };
           if(!this.validLettersName(this.pharmacist.name)){
                alert("Please enter valid name, use only letters!")
                return;
            }
            if(!this.validLettersSurname(this.pharmacist.surname)){
                alert("Please enter valid surname, use only letters!")
                return;
            }
            if(!this.validPhone(this.pharmacist.contact)){
                alert("Please enter valid contact number (10 digits)!")
                return;
            }
            if(!this.validLettersCountry(this.pharmacist.address.country)){
                alert("Please enter valid conutry, use only letters!")
                return;
            }
             if(!this.validLettersCity(this.pharmacist.address.town)){
                alert("Please enter valid city name, use only letters!")
                return;
            }
            if(!this.validLettersStreet(this.pharmacist.address.street)){
                alert("Please enter valid street name, use only letters!")
                return;
            }
            if(this.pharmacist.address.number == ""){
                alert("Please enter street number!")
                return;
            }
            if(this.pharmacist.address.number < 0){
                alert("Please enter valid street number!")
                return;
            }
            this.axios.post('/pharmacist/update',pharmacistInfo, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert("Successfully edited profile.")
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
            
        },
        updatePassword : function () {
            if(this.pharmacist.newPassword != this.pharmacist.repeatNewPassword) {
            alert("New passwords are not equals!")
            return;
            }
            const changePassword ={
                password : this.pharmacist.currentPassword,
                confirmPassword : this.pharmacist.newPassword
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/pharmacist/updatePassword',changePassword, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                     alert(response.data)
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        }
      
    }
}
</script>

<style scoped>
    .card {
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        max-width: 600px;
        margin: auto;
        text-align: center;
        font-family: arial;
    }
    .homepage_style{
        background: #17a2b8; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 70px;
    }
    .profile_style{
        position: fixed;
        top: 1;
        left: 30%;
        right:30%;
        width: 100%;
    }
    .space_style{
        margin-right:5px
    }
    .profile-img {
        width: 100%;
        position: relative;
    }

    .img-responsive {
        max-width:100%;
        height:auto;
    }

    .img-circle {
        position:absolute;
        z-index:99;
        left:10px;
        bottom:-50%;
    }
    .top-buffer { 
    margin-top:20px; 
    }
</style>