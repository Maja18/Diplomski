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
        
        <div style = "background-color:white; margin: auto; width: 40%;border: 1px solid black;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <b><label >Apoteka</label></b>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
                   
               </div>
               <div v-for="pharmacy in pharmacies"   v-bind:key="pharmacy.id">
                    <div class="row">
                            <div class=" form-group col">
                                <label >{{pharmacy.pharmacyName}}</label>
                            </div>
                           
                            <div class=" form-group col">
                                <b-button pill variant="info"  style="margin-top:-7pxč width:170px" class="btn btn-primary" v-on:click = "canclePromotion($event,pharmacy)" >Otkaži promociju</b-button>
                            </div>
                    </div>
               </div>
     </div>
    </div>
    
</template>


<script>

export default {
    name: 'Profile',
     data() {
    return {

        patient : "",
        pharmacies:[],
    }
  },
  mounted() {
       this.getMyPromotions()
    },
    methods:{
        
         showSubsribedPharmacies: function(){
          this.getMyPromotions();
        },
        showPharmacyPage: function(){
           window.location.href = '/';
        },
        showEPrescription: function(){
           window.location.href = '/ePrescription';
        },
        writeComplaint: function(){
           window.location.href = '/complaint';
        },
        showProfile: function(){
           window.location.href = '/profileInfo';
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
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
             if(!this.pharmacies.length){
                 alert("You are not subscribed to any pharmacy, please visit our home page to do so!")
             }
         }).catch(res => {
                console.log(res.response.data.message);
            });
        },
         canclePromotion : function(event, pharmacy) {
            const pharmacyInfo ={
                pharmacyId : pharmacy.pharmacyId,
                pharmacyName: pharmacy.pharmacyName,
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/promotion/canclePromotion',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    this.pharmacies = response.data;
                    this.getMyPromotions()
                   alert("Promotion is successfully cancled!"); 
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      },
       
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