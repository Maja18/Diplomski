<template>
    <div id="pharmacies">
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
      <b-form-input type="search" placeholder="ime apoteke"></b-form-input>
    </b-input-group>
                <b-button pill style="margin-left:1%; margin-top:-10px; width:100px"  variant="info">Traži</b-button>
               
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
                        <b-icon icon="caret-down-fill"  aria-hidden="true"> </b-icon>
                        <b-icon icon="caret-up-fill"  aria-hidden="true"> </b-icon>
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
                    <tr v-for="pharmacy in pharmacies" v-bind:key="pharmacy.id"
                    >
                    <td>{{pharmacy.name}}</td>
                    <td>{{pharmacy.city}}</td>
                    <td>{{pharmacy.street}} {{pharmacy.number}}</td>
                     <td>{{pharmacy.grade}}</td>
                   
                    </tr>
                </tbody>
            </table>
            <div> 
        </div>
    </div>
    
    </div>
</template>

<script>
export default {
    name: 'Pharmacies',
     data() {
    return {
       pharmacies :[],
       drugName : "",
       showMedicationInfoDiv : false, 
       selectedDrugId:0,
       selectedDrugCode:'',
       pharmacyDrugAvailability: [],
       sortBy: 'grade',
       sortDesc: false,
       fieldForSorting: "grade",
       fields: [
          { key: 'name', label: 'Ime apoteke' },
          { key: 'type', label: 'Grad' },
          { key: 'grade', label: 'Ulica' },
          {label: 'Ocena '},
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
       this.getPharmacies()
    },
  methods:{
        getPharmacies : function() {
          //let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.pharmacies = []
          this.axios.get('/pharmacy/pharmacies',{ 
            // headers: {
               //  'Authorization': 'Bearer ' + token,
             //}
         }).then(response => {
            // this.isAuthorized = true;
             this.pharmacies=response.data;
         }).catch(res => {
                //this.isAuthorized = false;
                //alert("Please, log in first!");
                //window.location.href = "/login";
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