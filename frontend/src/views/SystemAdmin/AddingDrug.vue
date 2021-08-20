<template>
  <div id="addingDrug">
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
         <!-- DRUG REGISTRATION -->
     <div style="background-color:#174452; margin: auto; width: 40%;padding: 10px;margin-top:45px;">
                
                    <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                        <label style="color:white;text-align:left; margin-top:10px">Ime leka:</label>
                        <input type="text" class="form-control" v-model="name" placeholder="brufen">
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                        <label style="color:white;text-align:left">Kod:</label>
                        <input type="text" class="form-control" v-model = "code" placeholder="M65">
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                            <label style="color:white;text-align:left">Loalty poeni:</label>
                            <input type="number" class="form-control" v-model="points" placeholder="0">
                        </div>
                    </div>
                    <!-- DRUG TYPE -->
                   <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                             <label style="color:white; text-align:left">Tip:
                                 <b-dropdown v-model="type" variant="info" style="width:150px; margin-left:107px" text="izaberite tip">
                          <b-dropdown-item  value="antibiotic">antibiotic</b-dropdown-item>
                          <b-dropdown-item value="antihistamine">antihistamine</b-dropdown-item>
                          <b-dropdown-item value="taAntidepressantblet">taAntidepressantblet</b-dropdown-item>
                       </b-dropdown>
                             
                    </label>
                        </div>
                   </div>
                     <!-- DRUG TYPE -->
                <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                               <label style="color:white;text-align:left">Forma:
                                   <b-dropdown v-model="form" variant="info" style="width:150px; margin-left:83px" text="izaberite formu">
                          <b-dropdown-item  value="gel">gel</b-dropdown-item>
                          <b-dropdown-item value="powder">prašak</b-dropdown-item>
                          <b-dropdown-item value="tablets">tableta</b-dropdown-item>
                       </b-dropdown>
                         
                           </label> 
                        </div>
                   </div>
                            <!-- DRUG WAY OF SELLING -->
                            <div class="form-row d-flex justify-content-center">
                        <div class="form-group col-md-6">
                     <label style="color:white;text-align:left">Način prodaje:
                          <b-dropdown v-model="wayOfSelling" variant="info" style="width:150px; margin-left:31px" text="izaberite način">
                          <b-dropdown-item  value="onPrescription">na recept</b-dropdown-item>
                          <b-dropdown-item value="nonPrescription">bez recepta</b-dropdown-item>
                       </b-dropdown>
                     </label>
                        </div>
                            </div>
                   <div class="form-row d-flex justify-content-center">
                         <div class="form-group col"></div>

                        <div class="form-group col">
                            <b-button pill variant="info" style="width:210px; margin-top:10px" v-on:click = "showSpecificationModal">Dodaj specifikaciju</b-button>
                        </div>
    
                        <div class="form-group col">
                            <b-button pill variant="info" style="width:210px;margin-top:10px" v-on:click = "showAlternativesModal">Dodaj alternativne lekove</b-button>
                        </div>
                          <div class="form-group col"></div>

                    </div>
                   
                    <b-button pill variant="info" style="width:200px ;margin-top:20px" v-on:click = "addDrugs">POTVRDI</b-button>
            </div>
       <div> 
          <b-modal ref="specification-modal" hide-footer scrollable title="Fill drug specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div class="modal-body">
                         <div class="form-group">
                              <label>Define contra indications:</label>
                              <input type="text" class="form-control" v-model="specification.contraIndications" placeholder="Enter contra indications">
                         </div>
                         <div class="form-group">
                              <label>Define composition:</label>
                              <input type="text" class="form-control" v-model="specification.composition" placeholder="Enter composition">
                         </div>
                         <div class="form-group">
                              <label>Define recommended consumption on a day:</label>
                              <input type="number" class="form-control" v-model="specification.therapyDuration" placeholder="Enter recommended consumption">
                         </div>
                              <div class="form-group">
                              <label>Manufacturer:</label>
                              <input type="text" class="form-control" v-model="specification.manufacturer" placeholder="Enter manufacturer">
                         </div>
                         <button v-on:click = "confirmSpecification" class="btn btn-primary">Confirm</button>        
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
        <b-modal ref="alternatives-modal" hide-footer scrollable title="Fill medicine specification" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group col-md-6">
                               <label style="color:white">Alternatives:</label>
                              <b-dropdown id="ddCommodity" name="ddCommodity" text="Choose alternatives" class = "btn btn-link btn-lg" style="float:left;margin-left:20px;color:white">
                                        <b-dropdown-item v-for="item in this.allDrugs"  v-on:click ="addAlternativeTolist($event, item)" v-bind:key="item.id"> {{item.name }}</b-dropdown-item>
                              </b-dropdown> 
                        </div>
                        <div class="form-group">
                            <hr/>
                        </div>
                        
                        <button v-on:click = "confirm" class="btn btn-primary">Confirm</button>        
                    </div>                
                </div>
            </div>
        </b-modal>
    </div>

</template>


<script>
export default {
    name: 'AddingDrug',
    
  data() {
    return {
      type:'',
      form:'',
      wayOfSelling:'',
      
      name : "",
      code : "",
      points:"",
      specification : {
           contraIndications : "",
           composition : "",
           therapyDuration : "", 
           manufacturer : ""
      },
      allDrugs : null,
      alternativeDrugs : [],
      isAuthorized : false,
      clicked : false,
    }
  },
  mounted() {
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
     this.axios.get('/drug/getdrugs',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.allDrugs = response.data;
         }).catch(res => {
                        alert("Please log in again or try later.");
                        console.log(res);
                 });
    },
   methods:{
       validLettersName: function (name) {
        var res = /^[a-zA-Z]+$/;
        return res.test(name);
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
          showSupplierRegistration: function(){
           window.location.href = "/supplierRegistration";
        },
        showSpecificationModal : function() {
          this.$refs['specification-modal'].show();
          this.clicked = true
      },
        showComplaints: function(){
           window.location.href = "/complaints";
        },
        showPharmacyAdminRegistration: function(){
           window.location.href = "/pharmacyAdminRegistration";
        },
        showSystemAdminRegistration: function(){
           window.location.href = "/systemAdminRegistration";
        },
         showDrugManipulation: function(){
           window.location.href = "/addingDrug";
        },
      confirmSpecification : function() {
        this.$refs['specification-modal'].hide()
      },
       showLoalty: function(){
           window.location.href = "/loaltyProgram";
        },
      logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
         showAlternativesModal : function() {
          this.$refs['alternatives-modal'].show();
      },
      addAlternativeTolist : function(event, item) {
           this.alternativeDrugs.push(item);
      },
       confirm : function() {
          this.$refs['alternatives-modal'].hide()
      },
      
      addDrugs : function() {
          const medicationInfo = {
               name : this.name,
               code : this.code,
               drugForm : this.form,
               type : this.type,
               points :this.points,
               wayOfSelling : this.wayOfSelling,
               specification : this.specification,
               alternativeDrugCodes : this.alternativeDrugs
          }
          let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

          if(!this.validLettersName(this.name)){
                alert("Please enter valid name!")
                return;
            }
           if (this.code == "") {
             alert("Please enter code!")
             return;
            }
             if(this.points == ""){
                alert("Please enter valid points!")
                return;
            }
             if(this.points < 0){
                alert("Please enter valid points!")
                return;
            }
            if(this.type == ""){
                alert("Please enter type!")
                return;
            }
            if(this.form == ""){
                alert("Please enter form!")
                return;
            }
            if(this.wayOfSelling == ""){
                alert("Please enter way of selling!")
                return;
            }
            if(this.clicked == false){
                alert("Please add specification!")
                return;
            }

          this.axios.post('/drug/addDrug',medicationInfo,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }}).then(response => {
                       alert("Drug is added!");
                        console.log(response.data);
                        
                })
                .catch(res => {
                    alert(res.response.data.message);
                 });    

      },
      
     
      
      
     
      

      
},
}
</script>  

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f6f9fa!important;
  min-height: 70vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
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

    .btn-primary{
        background: #403f72;
        position:relative; 
    }
    select{
        background: #ffffff;
        float:right;
        margin-right:60px;
        margin-left: 10px;
    }

    label, input {
    display: block;
}
    label {
    margin-bottom: 20px;
}





</style>