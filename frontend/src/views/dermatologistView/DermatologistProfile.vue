<template>
    <div id="dermatologistProfile">
        <!-- Navigation bar -->
        <div class="homepage_style "  >
            <span style="float: left; margin: 15px">
                <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
                <b-button style="margin-left:20px" pill class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profil</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">Pacijenti</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Radni kalendar</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Odmor</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showExaminations">Pregledi</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showNewExamination">Nov pregled</b-button>
            </span>
            <span  style="float:right;margin:15px">
             <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "logOut">
                     <b-icon icon="power"   aria-hidden="true"></b-icon> Odjavi se
            </b-button>
            </span>
        </div>
        <!-- Navigation bar bg-white mt-5 mb-5-->
        <div class="container bg-white   mt-5 mb-5">
   
            <div class="card"  >
                <div class="profile-img">
                    <img class="img-responsive" src="@/images/nature.jpg" style=" height:150px;" width="100%" />
                    <img class="img-circle img-responsive rounded-circle"  src="https://w7.pngwing.com/pngs/739/813/png-transparent-woman-wearing-white-lab-coat-sildenafil-medicine-health-care-pharmaceutical-drug-physician-pharmacist-hospital-pharmacy-service-pharmaceutical-drug-medicine-thumbnail.png" style="width:120px; height:120px;"  />
                </div>
                <div class=" d-inline-block " style=" height:100%; background-color: #ced2d3;">
                    <h4 style = "position:left; left:60px; top:2px; background-color:#ebf0fa;"><b>dr {{dermatologist.name}}  {{dermatologist.surname}} </b></h4>
                    <div class="p-3 py-5 ">
                    <div class="col-md-8 ">
                    <div class="card mb-3 " style = "position:right; left:90px; top:2px; background-color:#ebf0fa;">
                    <div class="card-body" style="background-color: #ebf0fa;">
                        <h4 class ="text-justify top-buffer"> Ime:   {{dermatologist.name}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Prezime:   {{dermatologist.surname}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Email:   {{dermatologist.email}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Kontakt:   {{dermatologist.contact}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Adresa:   {{dermatologist.address.street}}, {{dermatologist.address.number}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Grad:  {{dermatologist.address.town}}, {{dermatologist.address.country}} </h4>
                        
                </div>
              </div>
            </div>
                        
                         <!-- <div class="mt-5 text-center top-buffer"><button class="btn btn-info btn-lg space_style" style="background-color:#003d66;"  v-on:click = "editProfile">Edit profile</button></div>-->  
                        <!-- Modal --> 
                        <div class="mt-5 text-center top-buffer">
                           
                            <b-button pill class="btn btn-info btn-lg space_style" variant="info" style="width:170px ; margin-left:0px" v-b-modal.modal-2>Izmeni šifru</b-button>
                            <b-button  pill class="btn btn-info btn-lg space_style" variant="info" style="width:170px; margin-left:30px" v-b-modal.modal-1>Izmeni podatke</b-button>
                            <b-modal ref="modal-ref" id="modal-1" title="Izmena ličnih podataka" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer"> Ime:
                                        <b-form-input type="text" v-model="dermatologist.name" label="First Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Prezime:
                                        <b-form-input v-model="dermatologist.surname" label="Last Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Email:
                                        <b-form-input v-model="dermatologist.email" l
                                        abel="Email" disabled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Kontakt:
                                        <b-form-input v-model="dermatologist.contact" filled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Ulica:
                                        <b-form-input v-model="dermatologist.address.street" filled placeholder="Enter your street name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Broj ulice:
                                        <b-form-input v-model="dermatologist.address.number" label="Street Number" filled placeholder="Enter your street number"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Grad:
                                        <b-form-input v-model="dermatologist.address.town" label="City" filled placeholder="Enter the city name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Država:
                                        <b-form-input v-model="dermatologist.address.country" label="Country" filled placeholder="Enter the country name"></b-form-input>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class="btn btn-info btn-lg space_style" style=" margin-left:30px;width:4cm;" v-on:click = "cancel">Odustani</b-button>
                                        <b-button pill variant="info" class="btn btn-info btn-lg space_style" style="margin-left:30px;width:4cm;" v-on:click = "update">Potvrdi</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                            <b-modal ref="modal-ref2" id="modal-2" title="Izmena šifre" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password"> Trenutna šifra</label>
                                        <VuePassword
                                            v-model="dermatologist.currentPassword"
                                            id="password1"
                                            placeholder="me123"
                                            :badge="false" 
                                            :toggle="true"
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Nova šifra:</label>
                                        <VuePassword
                                            v-model="dermatologist.newPassword"
                                            id="password1"
                                            placeholder="1234"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Potvrdite novu šifru:</label>
                                        <VuePassword
                                            v-model="dermatologist.repeatNewPassword"
                                            id="password1"
                                            placeholder="1234"
                                        />
                                        </div>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class= "btn btn-info btn-lg space_style" style=" margin-left:33px; width:4cm;" v-on:click = "cancelPassword">Odustani</b-button>
                                        <b-button pill variant="info" class="btn btn-info btn-lg space_style" style="margin-left:30px; width:4cm;" v-on:click = "updatePassword">Izmeni</b-button>
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
name: 'DermatologistProfile',
 components: {
    VuePassword,
  },
data() {
    return {
        dermatologist : [],
        name: "",
        surname: "",
        email: "",
        address :[],
        password : "",
        currentPassword : "",
        newPassword : "",
        repeatNewPassword : "",
        contact: "",
    }
  },
  mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/profile',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.dermatologist = response.data;
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
           window.location.href = "/dermatologistHomepage";
        },
        showProfile: function(){
           window.location.href = "/dermatologistProfile";
        },
        showPatients: function(){
            window.location.href = "/dermatologistPatients";
        },
        showWorkCalendar: function(){
            window.location.href = "/dermatologistWorkCalendar";
        },
        showAbsenceRequest: function(){
            window.location.href = "/dermatologistAbsenceRequest";
        },
        showExaminations: function(){
            window.location.href = "/dermatologistExamination/-1";
        },
        showNewExamination: function(){
            window.location.href = "/dermatologistNewExamination";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
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
        editProfile: function(){
            window.location.href = "/dermatologistNewExamination";
        },
        cancel() {
            this.$refs['modal-ref'].hide();
        },
        cancelPassword() {
            this.$refs['modal-ref2'].hide();
        },
        update : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const addressInfo = {
                town: this.dermatologist.address.town,
                street: this.dermatologist.address.street,
                number:this.dermatologist.address.number,
                country: this.dermatologist.address.country
            };
            const dermatologistInfo = {
                name: this.dermatologist.name,
                surname : this.dermatologist.surname,
                email:this.dermatologist.email,
                address: addressInfo,
                contact: this.contact,
           };
            if(!this.validLettersName(this.dermatologist.name)){
                alert("Please enter valid name!")
                return;
            }
            if(!this.validLettersSurname(this.dermatologist.surname)){
                alert("Please enter valid surname!")
                return;
            }
            if(!this.validPhone(this.dermatologist.contact)){
                alert("Please enter valid contact number (10 digits)!")
                return;
            }
            if(!this.validLettersCountry(this.dermatologist.address.country)){
                alert("Please enter valid conutry!")
                return;
            }
             if(!this.validLettersCity(this.dermatologist.address.city)){
                alert("Please enter valid city!")
                return;
            }
            if(!this.validLettersStreet(this.dermatologist.address.street)){
                alert("Please enter valid street!")
                return;
            }
            if(this.dermatologist.address.number == ""){
                alert("Please enter street number!")
                return;
            }
            if(this.dermatologist.address.number < 0){
                alert("Please enter valid street number!")
                return;
            }
            this.axios.post('/dermatologist/update',dermatologistInfo, { 
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
            if(this.dermatologist.newPassword != this.dermatologist.repeatNewPassword) {
            alert("New passwords are not equals!")
            return;
            }
            const changePassword ={
                password : this.dermatologist.currentPassword,
                confirmPassword : this.dermatologist.newPassword,
                newPassword : this.dermatologist.newPassword
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/dermatologist/updatePassword',changePassword, { 
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
        height: 73px;
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