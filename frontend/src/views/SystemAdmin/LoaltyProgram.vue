<template>
    <div id="adminProfile">
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
          <div class="vue-tempalte" style="margin-top:-20px">
       
            <h3 style="margin-top:30px">Definisanje loalty programa</h3>

            <div class="form-group" style="margin-top:30px">
                 
                 <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Poeni za savetovanje</label>
                        <input type="number" class="form-control" v-model="consultingPoints" placeholder="Enter points">
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Poeni za pregled</label>
                        <input type="number" class="form-control" v-model = "examinationPoints" placeholder="Enter points">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Regular korisnički popust</label>
                        <input type="email" class="form-control" v-model="regularDiscount" placeholder="Enter discount">
                        </div>
                        
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Silver korisnički popust</label>
                        <input type="text" class="form-control" v-model="silverDiscount" placeholder="Enter discount">
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Minimun poena za silver</label>
                        <input type="number" class="form-control" v-model="minimumSilverPoints" placeholder="Enter points">
                        </div>
                    </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Gold korisnički popust</label>
                        <input type="text" class="form-control" v-model="goldDiscount" placeholder="Enter discount">
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Minimun poena za gold</label>
                        <input type="number" class="form-control" v-model="minimumGoldPoints" placeholder="Enter points">
                        </div>
                    </div>
                   <b-button pill variant="info" style="width:170px; margin-top:20px" v-on:click = "update" class="btn btn-primary">Potvrdi</b-button>
            </div>
    </div>
    </div>
    
</template>



<script>
export default {
    name: 'LoaltyProgram',
     data() {
    return {
            consultingPoints: 0,
            examinationPoints: 0,
            minimumSilverPoints: 0,
            minimumGoldPoints: 0,
            regularDiscount: "0%",
            silverDiscount: "0%",
            goldDiscount: "0%",
       
    }
  },

    methods:{
        validDiscount: function (regularDiscount) {
        var res = /^\d{1,2}%$/;
        return res.test(regularDiscount);
       },
       validDiscountSilver: function (silverDiscount) {
        var res = /^\d{1,2}%$/;
        return res.test(silverDiscount);
       },
       validDiscountGold: function (goldDiscount) {
        var res = /^\d{1,2}%$/;
        return res.test(goldDiscount);
       },
         showProfile: function(){
           window.location.href = "/profileData";
        },
         showLoalty: function(){
           window.location.href = "/loaltyProgram";
        },
        showSystemAdminRegistration: function(){
           window.location.href = "/systemAdminRegistration";
        },
        showDermatologyRegistration: function(){
           window.location.href = "/dermatologyRegistration";
        },
        showPharmacyRegistration: function(){
           window.location.href = "/pharmacyRegistration";
        },
          showSupplierRegistration: function(){
           window.location.href = "/supplierRegistration";
        },
        showPharmacyAdminRegistration: function(){
           window.location.href = "/pharmacyAdminRegistration";
        },
        showDrugManipulation: function(){
           window.location.href = "/addingDrug";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        showComplaints: function(){
           window.location.href = "/complaints";
        },
        update : function() {
            const loyaltyProgram ={
               examinationPoints: this.examinationPoints,
               consultingPoints: this.consultingPoints,
               regularDiscount: this.regularDiscount,
               silverDiscount: this.silverDiscount,
               goldDiscount:this.goldDiscount,
               minimumSilverPoints:this.minimumSilverPoints,
               minimumGoldPoints:this.minimumGoldPoints,
          }
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.examinationPoints <= 0){
                alert("Please enter positive number for points!")
                return;
            }
            if(this.consultingPoints <= 0){
                alert("Please enter positive number for points!")
                return;
            }
            if(!this.validDiscount(this.regularDiscount)){
                alert("Please enter valid discount (example 15%)!")
                return;
            }
            if(!this.validDiscountSilver(this.silverDiscount)){
                alert("Please enter valid discount (example 15%)!")
                return;
            }
            if(!this.validDiscountGold(this.goldDiscount)){
                alert("Please enter valid discount (example 15%)!")
                return;
            }
            if(this.minimumSilverPoints <= 0){
                alert("Please enter positive number for points!")
                return;
            }
            if(this.minimumGoldPoints <= 0){
                alert("Please enter positive number for points!")
                return;
            }
            
           this.axios.post('/loyaltyProgram/defineLoaltyProgram',loyaltyProgram,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }})
                .then(response => {
                       alert("Loyalty program is successfully defined!");
                        console.log(response.data);
                })
                .catch(res => {
                    alert(res.response.data.message);
                 });    
      },
      
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
  max-width: 900px;
  margin: 0 auto 20px;
  padding: 20px;
  text-align: center;
  margin: auto;
  
    }

.button {
   background:  #174452;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(25, 40, 90); 
   padding: 10px 20px;
   position: absolute;
   top: 110%;
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