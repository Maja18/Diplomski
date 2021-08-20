<template>
    <div id="showDrugs">
        <div style="background: white; height: 60px; margin-top: 20px">
          <h4 class="text-left"
        style="margin-left:2%;
        margin-top:1%;">
        </h4>
        
        <div
        style="margin-top: 2%;
        margin-left: 4%;">
            <b-form inline>
                 <b-input-group size="sm" class="mb-2">
      <b-input-group-prepend is-text>
        <b-icon icon="search"></b-icon>
      </b-input-group-prepend>
      <b-form-input type="search" placeholder="ime leka"></b-form-input>
    </b-input-group>
                <b-button pill style="margin-left:1%; margin-top:-10px" v-on:click = "searchDrug" variant="info">Traži</b-button>
               
            </b-form>
        </div>  
        <!-- TABLE -->
       <div style="height:35px"></div>
           
            <table class="table table-striped" style="width:90%; margin-left:65px">
                <thead class="thead-light" v-if="searched == 0">
                    <tr>
                    <th scope="col" 
                    v-for="f in fields" v-bind:key="f.key" 
                    @click="fieldForSorting = f.key"
                    >
                        {{f.label}}
                        <b-icon icon="caret-down-fill" @click="sortColumnA" aria-hidden="true"> </b-icon>
                        <b-icon icon="caret-up-fill" @click="sortColumnD" aria-hidden="true"> </b-icon>
                    </th>
                    </tr>
                </thead>
                 <thead class="thead-light" v-else>
                    <tr>
                    <th scope="col" 
                    v-for="f in fields" v-bind:key="f.key" 
                    @click="fieldForSorting = f.key"
                   >
                        {{f.label}}
                    </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="drug in drugs" v-bind:key="drug.id"
                    @click="selectedDrug = drug.id">
                    <td>{{drug.name}}</td>
                    <td>{{drug.type}}</td>
                    <td>{{drug.numberOfGrades}}</td>
                    <td>
                    <b-button pill style="background-color:#17a2b8" class="btn btn-primary" v-on:click = "showDrugSpecification($event,drug)" >See Specification</b-button>
                    </td>
                    <td><b-button pill style="background-color:#17a2b8" class="btn btn-primary" v-on:click = "checkAvailability($event,drug)">See Availability</b-button> </td>
                    </tr>
                </tbody>
            </table>
            <div> 
        </div>
    </div>
    <div> 
          <b-modal ref="quantity-modal" hide-footer scrollable title="Drug availability" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div  class="modal-body">
                             <div class="row">
                    <div class=" form-group col">
                        <b><label >Apoteka</label></b>
                    </div>
                    <div class=" form-group col">
                        <b><label >Cena</label></b>
                    </div>
                   
               </div>
                             <div v-for="pharmacy in pharmacyDrugAvailability" v-bind:key="pharmacy.id" class="row">
                                <div class=" form-group col">
                                <label >{{pharmacy.pharmacyName}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{pharmacy.price}}</label>
                            </div>
                           
                             </div>
                                                       
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>

       <div> 
          <b-modal ref="quantity-modal2" hide-footer scrollable title="Drug specification" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:whitesmoke">
                         <div  class="modal-body">
                             <div class="row">
                    <div class=" form-group col">
                        <b><label >Kod</label></b>
                    </div>
                    <div class=" form-group col">
                        <b><label >Forma</label></b>
                    </div>
                    <div class=" form-group col">
                       <b> <label >Kompozicija</label></b>
                    </div>
                    <div class=" form-group col">
                        <b><label >Proizvođac</label></b>
                    </div>
                     <div class=" form-group col">
                       <b> <label >Idavanje na</label></b>
                    </div>
                     <div class=" form-group col">
                       <b> <label >Kontraindikacije</label></b>
                    </div>
               </div>
                             <div class="row">
                                <div class=" form-group col">
                                <label >{{drugInfo.code}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{drugInfo.drugForm}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{drugInfo.specification.composition}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{drugInfo.specification.manufacturer}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{drugInfo.issuance}}</label>
                            </div>
                            <div  class=" form-group col">          
                                <label >{{drugInfo.specification.contraIndications}}</label>
                            </div>
                             </div>
                                                       
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
    </div>
</template>

<script>
export default {
    name: 'ShowDrugs',
     data() {
    return {
       drugs :[],
       drugName : "",
       showMedicationInfoDiv : false, 
       selectedDrugId:0,
       selectedDrugCode:'',
       pharmacyDrugAvailability: [],
       sortBy: 'grade',
       sortDesc: false,
       fieldForSorting: "grade",
       fields: [
          { key: 'name', label: 'Name' },
          { key: 'type', label: 'Type' },
          { key: 'grade', label: 'Grade' },
          {label: 'Specification'},
          { label: 'Availability'}
        ],
        selectMode: 'single',
       drugInfo : {
            name : "",
            form : "",
            type :"",
            issuanceRegime :"",
            numberOfGrades:0,
            mark : 0,
            specification: {
                    contraIndications :"",
                    structure : "",
                    recommendedConsumption : "",
                    manufacturer : ""
            },
            medicationId : 0,
            code : 0
      },
      searched: 0,
    }
  },
  mounted() {
       this.getDrugs()
    },
  methods:{
        getDrugs : function() {
          //let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.drugs = []
          this.axios.get('/drug/getdrugs',{ 
            // headers: {
               //  'Authorization': 'Bearer ' + token,
             //}
         }).then(response => {
            // this.isAuthorized = true;
             this.drugs=response.data;
         }).catch(res => {
                //this.isAuthorized = false;
                //alert("Please, log in first!");
                //window.location.href = "/login";
                console.log(res);
            });
      },
      searchDrug : function(){
            //let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.drugName == ""){
                alert("Please enter name!")
                return;
            }
            const drugForSearch = {
                name: this.drugName,
            };
            this.axios.post('/drug/searchDrug',drugForSearch, { 
               // headers: {
                //'Authorization': 'Bearer ' + token,
                })
                .then(response => {
                    this.drugs = response.data;
                    this.showMedicationInfoDiv = true;
                    alert("successfully")
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },
        checkAvailability : function(event, drug) {
          this.selectedDrugId = drug.id;
          this.selectedDrugCode = drug.code;
          const drugInfos ={
                id : this.selectedDrugId,
                drugCode : this.selectedDrugCode
            } 
            //let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/pharmacy/checkDrugAvailability',drugInfos,{ 
                        // headers: {
                               // 'Authorization': 'Bearer ' + token,
                }).then(response => {
                    this.pharmacyDrugAvailability = response.data
                    alert("successfully");
                    this.$refs['quantity-modal'].show();
                    console.log(response); 
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      },
      showDrugSpecification : function(event, drug) {
          this.selectedDrugId = drug.id;
          const drugInfos1 ={
                id : this.selectedDrugId,
            } 
           // let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('/drug/getDrugSpecification',drugInfos1,{ 
                         //headers: {
                                //'Authorization': 'Bearer ' + token,
                }).then(response => {
                    this.drugInfo = response.data
                    alert("successfully");
                    this.$refs['quantity-modal2'].show();
                    console.log(response); 
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
          
          
      },
      sortColumnA : function() {
           // let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/drug/sortResult',columnForSort, { 
               // headers: {
               // 'Authorization': 'Bearer ' + token,
                })
                .then(response => {
                    alert("ok")
                    this.drugs = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },

        sortColumnD : function() {
           // let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
           if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/drug/sortResultDescending',columnForSort, { 
               // headers: {
               // 'Authorization': 'Bearer ' + token,
                })
                .then(response => {
                    alert("ok")
                    this.drugs = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        }
     
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

     .th_header {
        display: table-cell;
        -ms-text-combine-horizontal: inherit;
        font-weight: bold;
        text-align: center;
        }

</style>