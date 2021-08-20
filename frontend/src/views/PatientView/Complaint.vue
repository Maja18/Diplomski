<template>
    <div id="complaint">
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
        <!-- *******************************************************-->
        <div class="row">
            <div class="form-group col">
                <h5 style="color: #0D184F;margin-top:20px;margin-left:-74px"> Pišete žalbu za : </h5>
            </div >
             <template >
              <div class="custom-control custom-radio form-group col " style="margin-top:20px">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample1" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'dermatology')">
                   <label class="custom-control-label" for="defaultGroupExample1">dermatolog</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col " style="margin-top:20px">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample2" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'pharmacist')">
                   <label class="custom-control-label" for="defaultGroupExample2">farmaceut</label>
                
              </div> 
              <div class="custom-control custom-radio form-group col " style="margin-top:20px">

                  <input type="radio" class="custom-control-input" id="defaultGroupExample3" name="groupOfDefaultRadios" v-on:click="updateFiler($event,'pharmacy')">
                 <label class="custom-control-label" for="defaultGroupExample3">apoteka</label>
                
              </div> 
              
             </template>
        </div>   
         <!-- DERMAATOLOGY -->
                <div v-if = "showDermatologistComplaint">
                    <div class="row" >
                        <div class="col d-flex justify-content-center" >
                            <b-dropdown id="ddCommodity" name="ddCommodity" variant="info" text="Izaberite dermatologa"  style="float:left;margin-left:20px; margin-top:20px">
                                <b-dropdown-item v-for="derm in this.dermatologists"  v-on:click ="dermatologistIsSelected($event, derm)" v-bind:key="derm.email"> {{derm.fullName }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                             <label for="name" style="margin-top:20px">Unesite opis žalbe:</label>
                        </div> 
                    </div>
                    <div class="row d-flex justify-content-center" style=" width:1300px">
                        <input type="textarea" style="height:300px; width: 100%;background-color:white;margin-left:240px" v-model="complaintText" class="form-control">
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                         <b-button pill variant="info" style="width:170px;margin-left:50px;margin-top:20px" class="btn btn-primary" @click="sendComplaintToDermatologist">Pošalji</b-button>
                    </div>
                </div>
                 <!-- PHARMACIST -->
                <div v-if = "showPharmacistComplaint">
                    <div class="row">
                        <div class="col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" variant="info" text="Izaberite farmaceuta" style="float:left;margin-left:20px; margin-top:20px">
                                <b-dropdown-item v-for="pharm in this.pharmacists"  v-on:click ="pharmacistIsSelected($event, pharm)" v-bind:key="pharm.email"> {{pharm.fullName }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                              <label for="name" style="margin-top:20px">Unesite opis žalbe:</label>
                        </div> 
                    </div>
                    <div class="row d-flex justify-content-center" style=" width:1300px">
                        <input type="textarea" style="height:300px; width: 100%;background-color:white;margin-left:240px" v-model="complaintText" class="form-control">
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                         <b-button pill variant="info" style="width:170px;margin-left:50px;margin-top:20px" class="btn btn-primary" @click="sendComplaintToPharmacist">Pošalji</b-button>
                    </div>
                </div>
                <!-- PHARMACY -->
                <div v-if = "showPharmacyComplaint">
                    <div class="row">
                        <div class="col d-flex justify-content-center">
                            <b-dropdown id="ddCommodity" name="ddCommodity" variant="info" text="Izaberite apoteku"  style="float:left;margin-left:20px;margin-top:20px">
                                <b-dropdown-item v-for="pharmacy in this.pharmacies"  v-on:click ="pharmacyIsSelected($event, pharmacy)" v-bind:key="pharmacy.id"> {{pharmacy.name }}</b-dropdown-item>
                            </b-dropdown> 
                        </div>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col">
                             <label for="name">Unesite opis žalbe:</label>
                        </div> 
                    </div>
                    <div class="row d-flex justify-content-center" style=" width:1300px">
                        <input type="textarea" style="height:300px; width: 100%;background-color:white;margin-left:240px" v-model="complaintText" class="form-control">
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                         <b-button pill variant="info" style="width:170px;margin-left:50px; margin-top:20px"  @click="sendComplaintToPharmacy" >Pošalji</b-button>
                    </div>
                </div>
            
        
    </div>
    
</template>


<script>

export default {
    name: 'Complaint',
     data() {
    return {
        showComplaintForm : false,
       pharmacies : [],
       pharmacy : null,
       pharmacists : [],
       pharmacist : null,
       dermatologists : [],
       dermatologist : null,
       showPharmacyComplaint : false,
       showPharmacistComplaint : false,
       showDermatologistComplaint : false,
       complaintText : "",

       
    }
  },
    methods:{
        
         showSubsribedPharmacies: function(){
          window.location.href = '/patientProfile';
        },
        showPharmacyPage: function(){
           window.location.href = '/';
        },
          writeComplaint: function(){
           window.location.href = '/complaint';
        },
        showEPrescription: function(){
           window.location.href = '/ePrescription';
        },
        showProfile: function(){
           window.location.href = '/profileInfo';
        },
         logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        updateFiler : function(event, filter) {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        if(filter==="dermatology") {
            this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = true;
           this.axios.get('/dermatologist/dermaInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.dermatologists=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});
        }
        
        else if(filter==="pharmacist")  {
            this.showPharmacyComplaint = false;
          this.showPharmacistComplaint = true;
          this.showDermatologistComplaint = false;
           this.axios.get('/pharmacist/pharmacistInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmacists=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});
        }else{
            this.showPharmacyComplaint = true;
          this.showPharmacistComplaint = false;
          this.showDermatologistComplaint = false;
           this.axios.get('/pharmacy/pharmacies',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => { 
                this.pharmacies=response.data;
            }).catch(res => {
                        alert("Please try again later.");
                        console.log(res);});
        }
      },
      dermatologistIsSelected : function(event, derm) {
          this.complaintText ="";
          this.dermatologist = derm;
      },
      pharmacistIsSelected: function(event, pharm) {
          this.complaintText ="";
          this.pharmacists = pharm;
      },
      pharmacyIsSelected: function(event, pharmacy) {
          this.complaintText ="";
          this.pharmacy = pharmacy;
      },
       sendComplaintToDermatologist : function() {
     
          const complaintInfo= {
            isAnswered : false,
            description : this.complaintText,
            answer : "",
            dermatologyId: this.dermatologist.userId,
            
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/complaint/addComplaintDermatologist',complaintInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!");

                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);

                });
      },
      sendComplaintToPharmacist : function() {
     
          const complaintInfo= {
            isAnswered : false,
            description : this.complaintText,
            answer : "",
            pharmacistId: this.pharmacists.userId,
            
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/complaint/addComplaintPharmacist',complaintInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!");

                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);

                });
      },
       sendComplaintToPharmacy : function() {
     
          const complaintInfo= {
            isAnswered : false,
            description : this.complaintText,
            answer : "",
            pharmacyId : this.pharmacy.id,
            
            
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          this.axios.post('/complaint/addComplaintPharmacy',complaintInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("Complaint is successfully sent!");

                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);

                });
      },
      getMyPromotions: function(){
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.pharmacies = []
          this.axios.get('/promotion/subscriptions',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             
             this.pharmacies=response.data;
         }).catch(res => {
                console.log(res);
            });
        },
      },
       
    
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