<template>
    <div id="patientProfile">
        <div class="homepage_style ">
            <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
                  <b-button pill  style="margin-left:30px" class = "btn btn-info btn-lg space_style" v-on:click = "showPharmacyPage">Pharmacy home page</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profil</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style"  v-on:click = "showSubsribedPharmacies">Pretplaćene apoteke</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "writeComplaint">Žalbe</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showEPrescription">ERecept</b-button>
            </span>
            <span  style="float:right;margin:15px">
             <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "logOut">
                     <b-icon icon="power"   aria-hidden="true"></b-icon> Odjavi se
            </b-button>
            </span>
        </div>
          
        <div class="container bg-white   mt-5 mb-5">
   
            <div class="card"  >
                <div class="profile-img">
                    <img class="img-responsive" src="@/images/nature.jpg" style=" height:150px;" width="100%" />
                    <img class="img-circle img-responsive rounded-circle"  src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aGFwcHklMjB3b21hbnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80" style="width:120px; height:120px;"  />
                </div>
               <div class=" d-inline-block " style=" height:100%; background-color: #ced2d3;">
                    <h4 style = "position:left; left:60px; top:2px; background-color:#ebf0fa;"><b>Korisnik: {{patient.name}}  {{patient.surname}} </b></h4>
                    <div class="p-3 py-5 ">
                    <div class="col-md-8 ">
                    <div class="card mb-3 " style = "position:right; left:90px; top:2px; background-color:#ebf0fa;">
                    <div class="card-body" style="background-color: #ebf0fa;">
                        <h4 class ="text-justify top-buffer"> Ime :  {{patient.name}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Prezime:   {{patient.surname}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Email:  {{patient.email}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Adresa: {{patient.address.street}}, {{patient.address.number}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Grad:   {{patient.address.town}}, {{patient.address.country}} </h4>
                        
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
                                        <b-form-input v-model="patient.name" label="First Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Prezime:
                                        <b-form-input v-model="patient.surname" label="Last Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Email:
                                        <b-form-input v-model="patient.email" label="Email" disabled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Ulica:
                                        <b-form-input v-model="patient.address.street" label="Street" filled placeholder="Enter your street name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Broj ulice:
                                        <b-form-input v-model="patient.address.number" label="Street Number" filled placeholder="Enter your street number"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Grad:
                                        <b-form-input v-model="patient.address.town" label="City" filled placeholder="Enter the city name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Država:
                                        <b-form-input v-model="patient.address.country" label="Country" filled placeholder="Enter the country name"></b-form-input>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class="btn btn-info btn-lg space_style" style=" margin-left:30px;width:4cm;" v-on:click = "giveUp">Odustani</b-button>
                                        <b-button  pill variant="info" class="btn btn-info btn-lg space_style" style="margin-left:30px;width:4cm;" v-on:click = "update">Potvrdi</b-button>
                                    </b-row>
                                </div>
                            </b-modal>
                            <b-modal ref="modal-ref2" id="modal-2" title="Izmena šifre" hide-footer>
                                <div>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Trenutna šifra</label>
                                        <VuePassword
                                            v-model="patient.currentPassword"
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
                                            v-model="patient.newPassword"
                                            id="password1"
                                            placeholder="1234"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Potvrdite novu šifru:</label>
                                        <VuePassword
                                            v-model="patient.repeatNewPassword"
                                            id="password1"
                                            placeholder="1234"
                                        />
                                        </div>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class= "btn btn-info btn-lg space_style" style=" margin-left:33px; width:4cm;" v-on:click = "giveUp2" >Odustani</b-button>
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
    name: 'ProfileDataPatient',
    components: {
    VuePassword,
  },
     data() {
    return {

        patient : "",
        name: "",
        surname: "",
        email: "",
        contact: "",
        address : "",
        password:"",
        currentPassword : "",
        newPassword : "",
        repeatNewPassword : ""
    }
  },
 mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/profile',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.patient=response.data;
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
    
   },
    methods:{
        toggle () {
        this.show = !this.show
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
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(city);
       },
       validLettersStreet: function (street) {
        var res = /^[a-zA-Z]+(\s[a-zA-Z]+)?$/;
        return res.test(street);
       },
         showSubsribedPharmacies: function(){
         window.location.href = '/patientProfile';
        },
        showPharmacyPage: function(){
           window.location.href = '/';
        },
        showEPrescription: function(){
           window.location.href = '/ePrescription';
        },
         showProfile: function(){
           window.location.href = '/profileInfo';
        },
        writeComplaint: function(){
           window.location.href = '/complaint';
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
         giveUp:function(){
          this.$refs['modal-ref'].hide();
        },
         giveUp2:function(){
          this.$refs['modal-ref2'].hide();
        },
        update : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const addressInfo = {
                town: this.patient.address.town,
                street: this.patient.address.street,
                number:this.patient.address.number,
                country: this.patient.address.country
            };
            const patientInfo = {
                name: this.patient.name,
                surname : this.patient.surname,
                email:this.patient.email,
                address: addressInfo
           };

            if(!this.validLettersName(this.patient.name)){
                alert("Please enter valid name!")
                return;
            }
            if(!this.validLettersSurname(this.patient.surname)){
                alert("Please enter valid surname!")
                return;
            }
            if(!this.validLettersStreet(this.patient.address.street)){
                alert("Please enter valid street!")
                return;
            }
            if(this.patient.address.number == ""){
                alert("Please enter streetNumber!")
                return;
            }
            if(this.patient.address.numberr < 0){
                alert("Please enter valid streetNumber!")
                return;
            }
            if(!this.validLettersCity(this.patient.address.town)){
                alert("Please enter valid city!")
                return;
            }
             if(!this.validLettersCountry(this.patient.address.country)){
                alert("Please enter valid conutry!")
                return;
            }
       
            this.axios.post('/patient/update',patientInfo, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert("Successfully edited profile.")
                    this.$refs['modal-ref'].hide();
                        console.log(response);
                })
                .catch(response => {
                    alert("Error, please try later.")
                    console.log(response);
                })
            
        },
         updatePassword : function () {
            if(this.patient.newPassword != this.patient.repeatNewPassword) {
            alert("New passwords are not equals!")
            return;
            }
            const changePassword ={
                password : this.patient.currentPassword,
                confirmPassword : this.patient.newPassword
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/patient/updatePassword',changePassword, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert("Successfully edited password.")
                    this.$refs['modal-ref2'].hide();
                        console.log(response);
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
        margin-right:1px
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

</style>
