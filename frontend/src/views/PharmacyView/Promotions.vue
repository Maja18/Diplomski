<template>
     <div id="promotions">
        
         <!-- PHARMACIES -->
                <b-dropdown text="Select pharmacy" variant="outline-info" class="dropdown_style" id="dropdown-divider">
                            <b-dropdown-item  
                            v-for="pharm in pharmacies" v-bind:key="pharm.id" :value="pharm.id"
                            v-on:click = "showPromotions($event,pharm)">
                                {{ pharm.name}}
                             </b-dropdown-item>
                    </b-dropdown>
                    <p class="ml-n mt-6 strong chosen"> <b>Selected pharmacy: {{ selectedPharmacyName}}</b></p>
        <!-- table -->
         <div style = "background-color:#white; margin: auto; width: 60%;border: 1px solid black;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                       <b> <label style="color:black">Opis</label></b>
                    </div>
                    <div class=" form-group col">
                        <b><label style="color:black">Pocetak</label></b>
                    </div>
                    <div class=" form-group col">
                       <b> <label style="color:black">Kraj</label></b>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
                   
               </div>
               <div v-for="promotion in promotions"   v-bind:key="promotion.id">
                    <div class="row">
                            <div class=" form-group col">
                                <label style="color:black">{{promotion.description}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label style="color:black">{{format_date(promotion.period.startDate)}}</label>
                            </div>
                             <div  class=" form-group col">          
                                <label style="color:black">{{format_date(promotion.period.endDate)}}</label>
                            </div>
                           
                            <div class=" form-group col">
                                <b-button  pill variant="info"  v-on:click = "subscribe($event,promotion)">Subscribe</b-button>
                            </div>
                    </div>
               </div>
     </div>
     </div>
</template>

<script>
import moment from 'moment'
export default {
    name: 'Promotions',
     data() {
    return {
       promotions :[],
       pharmacies:[''],
       selectedPharmacyID: '',
       selectedPharmacyName: "",
       selectedPromotionID:'',
       isAuthorized : false,
       patient:''
    }
  },
  mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/myAccount',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,

             }
         }).then(response => {
             this.isAuthorized = true;
             this.patient = response.data
              this.getPharmacies();
         
         }).catch(res => {
                       this.isAuthorized = false;
                       alert("Please log in first!");
                        window.location.href = "/login";
                        console.log(res);
                
                 });
      
    },
   
  methods:{
       format_date(value){
         if (value) {
           return moment(String(value)).format('YYYY-MM-DD')
          }
      },
       
  getPromotions : function() {
            const pharmacyInfo ={
                id : this.selectedPharmacyID,
                name : this.selectedPharmacyName,
                
                
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/promotion/promotions',pharmacyInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    this.promotions = response.data;
                    console.log(response); 
                }).catch(res => {
                       alert(res.response.data);
                        console.log(res);
                });
      },
       getPharmacies : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.pharmacies = []
          this.axios.get('/pharmacy/pharmacies',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             this.isAuthorized = true;
             this.pharmacies=response.data;
         }).catch(res => {
                this.isAuthorized = false;
                alert("Please, log in first!");
                window.location.href = "/login";
                console.log(res);
            });
      },
      showPromotions : function(event, pharm) {
          this.selectedPharmacyID = pharm.id;
          this.selectedPharmacyName = pharm.name;
          this.getPromotions();

      },
      subscribe : function(event, promotion) {
          this.selectedPromotionID = promotion.pharmacyId;
          const promotionInfo ={
                pharmacyId : promotion.pharmacyId,
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/promotion/subscribe',promotionInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    alert("You have successfully subscribed!");
                    console.log(response); 
                }).catch(res => {
                       alert(res.response.data.message);
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

    .promotions{
  margin-left: 50px;
  margin-right: 50px;
}

</style>