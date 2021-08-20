<template>
    <div id="supplierProfile">
        <div class="homepage_style ">
            <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
                  <b-button pill  style="margin-left:30px" class = "btn btn-info btn-lg space_style" v-on:click = "showPharmacyPage">Pharmacy home page</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profil</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showMyOffers">Pregled mojih ponuda</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showGiveOffers">Daj ponudu</b-button>
                    <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showMyDrugs">Lekovi</b-button>
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
                    <img class="img-circle img-responsive rounded-circle"  src="https://www.vhv.rs/dpng/d/260-2601176_transparent-woman-smiling-png-smiling-girl-png-png.png" style="width:120px; height:120px;"  />
                </div>
               <div class=" d-inline-block " style=" height:100%; background-color: #ced2d3;">
                    <h4 style = "position:left; left:60px; top:2px; background-color:#ebf0fa;"><b>Dobavljač: {{supplier.name}}  {{supplier.surname}} </b></h4>
                    <div class="p-3 py-5 ">
                    <div class="col-md-8 ">Ime
                    <div class="card mb-3 " style = "position:right; left:90px; top:2px; background-color:#ebf0fa;">
                    <div class="card-body" style="background-color: #ebf0fa;">
                        <h4 class ="text-justify top-buffer"> : Ime {{supplier.name}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Prezime:   {{supplier.surname}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Email:  {{supplier.email}}  </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Adresa: {{supplier.address.street}}, {{supplier.address.number}} </h4>
                        <hr>
                        <h4 class ="text-justify top-buffer"> Grad:   {{supplier.address.town}}, {{supplier.address.country}} </h4>
                        
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
                                        <b-form-input v-model="supplier.name" label="First Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Prezime:
                                        <b-form-input v-model="supplier.surname" label="Last Name" filled placeholder="Enter your name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Email:
                                        <b-form-input v-model="supplier.email" label="Email" disabled></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Ulica:
                                        <b-form-input v-model="supplier.address.street" label="Street" filled placeholder="Enter your street name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Broj ulice:
                                        <b-form-input v-model="supplier.address.number" label="Street Number" filled placeholder="Enter your street number"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Grad:
                                        <b-form-input v-model="supplier.address.town" label="City" filled placeholder="Enter the city name"></b-form-input>
                                    </h5>
                                    <h5 class ="text-justify top-buffer"> Država:
                                        <b-form-input v-model="supplier.address.country" label="Country" filled placeholder="Enter the country name"></b-form-input>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class="btn btn-info btn-lg space_style" style=" margin-left:30px;width:4cm;" >Odustani</b-button>
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
                                            v-model="supplier.currentPassword"
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
                                            v-model="supplier.newPassword"
                                            id="password1"
                                            placeholder="1234"
                                           
                                        />
                                        </div>
                                    </h5>
                                    <h5 class ="text-justify top-buffer">
                                        <div class="mb-4">
                                        <label for="password">Potvrdite novu šifru:</label>
                                        <VuePassword
                                            v-model="supplier.repeatNewPassword"
                                            id="password1"
                                            placeholder="1234"
                                        />
                                        </div>
                                    </h5>
                                    <b-row style="float: left; margin: 30px;">
                                        <b-button pill variant="info" class= "btn btn-info btn-lg space_style" style=" margin-left:33px; width:4cm;" >Odustani</b-button>
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
    name: 'ProfileDataSupplier',
    components: {
    VuePassword,
  },
     data() {
    return {

        supplier : "",
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
        this.axios.get('/supplier/profile',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.supplier=response.data;
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
         showProfile: function(){
           window.location.href = "/profileDataSupplier";
        },
        showMyOffers: function(){
           window.location.href = "/offers";
        },
        showMyDrugs:  function(){
           window.location.href = "/drugs";
        },
        showPharmacyPage: function(){
           window.location.href = '/';
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        showGiveOffers:  function(){
           window.location.href = "/giveOffers";
        },
        update : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const addressInfo = {
                town: this.supplier.address.town,
                street: this.supplier.address.street,
                number:this.supplier.address.number,
                country: this.supplier.address.country
            };
            const supplierInfo = {
                name: this.supplier.name,
                surname : this.supplier.surname,
                email:this.supplier.email,
                address: addressInfo
           };

            if(!this.validLettersName(this.supplier.name)){
                alert("Please enter valid name!")
                return;
            }
            if(!this.validLettersSurname(this.supplier.surname)){
                alert("Please enter valid surname!")
                return;
            }
            if(!this.validLettersStreet(this.supplier.address.street)){
                alert("Please enter valid street!")
                return;
            }
            if(this.supplier.address.number == ""){
                alert("Please enter streetNumber!")
                return;
            }
            if(this.supplier.address.numberr < 0){
                alert("Please enter valid streetNumber!")
                return;
            }
            if(!this.validLettersCity(this.supplier.address.town)){
                alert("Please enter valid city!")
                return;
            }
             if(!this.validLettersCountry(this.supplier.address.country)){
                alert("Please enter valid conutry!")
                return;
            }
       
            this.axios.post('/supplier/update',supplierInfo, { 
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
            if(this.supplier.newPassword != this.supplier.repeatNewPassword) {
            alert("New passwords are not equals!")
            return;
            }
            const changePassword ={
                password : this.supplier.currentPassword,
                confirmPassword : this.supplier.newPassword
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/supplier/updatePassword',changePassword, { 
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