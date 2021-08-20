<template>
<div id="drugs">
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
    <div style = "background-color:white; margin: auto; width: 60%;border: 1px solid black;padding: 10px;margin-top:45px;">
               <div class="row">
                    <div class=" form-group col">
                        <b><label >Naziv</label></b>
                    </div>
                    <div class=" form-group col">
                        <b><label >Kod</label></b>
                    </div>
                   
                    <div class=" form-group col">          
                        <b><label >Rezervisana količina</label></b>
                    </div>
                    <div class=" form-group col">          
                        <b><label >Dostupna količina</label></b>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
                   
               </div>
               <div v-for="drug in myDrugs"   v-bind:key="drug.code">
                    <div class="row">
                            <div class=" form-group col">
                                <label >{{drug.name}}</label>
                            </div>
                            <div class=" form-group col">          
                                <label >{{drug.code}}</label>
                            </div>
                           
                             <div class=" form-group col">          
                                <label >{{drug.reservedQuantity}}</label>
                            </div>
                             <div class=" form-group col">          
                                <label >{{drug.quantity}}</label>
                            </div>
                           
                            <div class=" form-group col">
                                <b-button pill variant="info" style="margin-top:-8px" v-on:click = "showDrugQuantity($event,drug)" class="btn btn-primary">Dodaj još količine</b-button>
                            </div>
                    </div>
               </div>
    </div>
    <div> 
          <b-modal ref="quantity-modal" hide-footer scrollable title="Količina leka" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                             <div class="row">
                                <div class=" form-group col">
                                     <label style="margin-top:7px">Unesite dostupnu količinu: </label> 
                                </div>
                                <div class=" form-group col">  
                                    <input type="text" class="form-control" v-model="choosenDrugQuantity" placeholder="Quantity..">
                                </div>
                             </div>
                              <b-button style="margin-left:250px; width:100px; margin-top:20px" pill variant="info" v-on:click = "giveUp" class="btn btn-primary">Odustani</b-button>
                            <b-button pill variant="info" style="margin-left:30px; width:100px; margin-top:20px" v-on:click = "changeQuantity" class="btn btn-primary">Potvrdi</b-button> 
                                                       
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
</div>
</template>

<script>
export default{
    name:'Drugs',
    data() {
    return {
        myDrugs : [],
        choosenDrug : {},
        choosenDrugQuantity : 0,
        choosenDrugId : null,
        
    
    }
    },
     methods:{
     showProfile: function(){
           window.location.href = "/profileDataSupplier";
        },
        showMyOffers: function(){
           window.location.href = "/offers";
        },
        showMyDrugs:  function(){
           window.location.href = "/drugs";
        },
        showGiveOffers:  function(){
           window.location.href = "/giveOffers";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        showPharmacyPage: function(){
           window.location.href = '/';
        },
        giveUp: function(){
          this.$refs['quantity-modal'].hide();
        },
      changeQuantity : function() {
            const quantityDrugInfo ={
                drugId : this.choosenDrugId,
                newQuantity : this.choosenDrugQuantity,
            } 
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/supplierDrugs/updateDrug',quantityDrugInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    this.getMyDrugs();

                   alert("Quantity is successfully updated!"); 
                }).catch(res => {
                       alert(res.response.data.message);
                        console.log(res);
                });
            this.$refs['quantity-modal'].hide();
            

      },
     
      showDrugQuantity : function(event, drug) {
          this.choosenDrug = drug;
          this.choosenDrugQuantity = drug.quantity;
          this.choosenDrugId = drug.id;
           this.$refs['quantity-modal'].show();
      },
      getMyDrugs : function() {
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.myDrugs = []
          this.axios.get('/supplierDrugs',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             
             this.myDrugs=response.data;
         }).catch(res => {
                console.log(res);
            });
      }
      
      
},
 mounted() {
        this.getMyDrugs();
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
