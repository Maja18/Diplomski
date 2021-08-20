<template>
    <div id="pharmacistPatients">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 50px; height: 50px; margin-right:10px;" src="@/images/natural-medicine.png">
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showHomepage">Home</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profile</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">My patients</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Work calendar</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Create a vacation</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showExaminations">Consaltings</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showNewExamination">Schedule new consalting</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showDispensingDrugs">Dispensing drugs</button>
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                </span>
        </div>
        <h4 class="text-left"
        style="margin-left:2%;
        margin-top:1%;">
        <b>Patient search:</b>
        </h4>
        <div
        style="margin-top: 2%;
        margin-left: 2%;">
            <b-form inline>
                <b-form-input
                v-model="nameForSerch"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Patient name"
                ></b-form-input>
                <b-form-input 
                v-model="surnameForSearch"
                placeholder="Patient last name">
                </b-form-input>
                <b-button style="margin-left:2%;" v-on:click = "searchPatient" variant="info">Search</b-button>
                <b-button style="margin-left:2%;" v-on:click = "showPatients" variant="info">Refresh</b-button>
            </b-form>
        </div>
        <div style="height:25px"></div>
            <h3>Patients</h3>
            <table class="table table-striped" style="width:100%;">
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
                    <tr v-for="patient in patients" v-bind:key="patient.email"
                    @click="selectedCounseling = patient.counselingId">
                    <td>{{patient.name}}</td>
                    <td>{{patient.surname}}</td>
                    <td>{{patient.email}}</td>
                    <td>{{patient.startDate}}</td>
                    <td>{{patient.startTime}}</td>
                    <router-link :to="{ name: 'PharmacistConsalting', params: {selectedCounseling: patient.examinationID}}" class="search-btn">
                        <b-button variant="info" style="margin-top:1%;">start examination</b-button>
                    </router-link>
                    </tr>
                </tbody>
            </table>
            <div> 
        </div>
    </div>
</template>

<script>
export default {
    name: 'PharmacistPatients',
    data() {
      return {
        fields: [
          { key: 'name', label: 'Name' },
          { key: 'surname', label: 'Surname' },
          { key: 'email', label: 'Email' },
          { key: 'startDate', label: 'Start date'},
          { key: 'startTime',label: 'Start time'},
          { key: 'btn', label: 'Chose examination'}
        ],
        selectedCounseling: '',
        patients: [],
        name: "",
        surname: "",
        email: "",
        nameForSerch: "",
        surnameForSearch: "",
        fieldForSorting: "name",
        searched: 0,
      }
    },
    mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacist/myPatients',{ 
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        }).then(response => {
            this.patients = response.data;
            
        }).catch(res => {
            alert("Error");
            console.log(res);
        });

    
    },
    methods:{
       showHomepage: function(){
           window.location.href = "/pharmacistHomepage";
        },
        showProfile: function(){
           window.location.href = "/pharmacistProfile";
        },
        showPatients: function(){
            window.location.href = "/pharmacistPatients";
        },
        showWorkCalendar: function(){
            window.location.href = "/pharmacistWorkCalendar";
        },
        showAbsenceRequest: function(){
            window.location.href = "/pharmacistAbsenceRequest";
        },
        showExaminations: function(){
            window.location.href = "/pharmacistConsalting/-1";
        },
        showNewExamination: function(){
            window.location.href = "/pharmacistNewConsalting";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        }, 
        showDispensingDrugs : function(){
            window.location.href = "/pharmacistDispensingDrugs";
        },
        searchPatient : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.nameForSerch == "" || this.surnameForSearch == ""){
                alert("Please enter name and last name!")
                return;
            }
            const patientForSearch = {
                name: this.nameForSerch,
                surname: this.surnameForSearch
            };
            this.axios.post('/pharmacist/searchMyPatient',patientForSearch, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.searched = 1;
                    this.patients = response.data;
                    this.fields = [
                    { key: 'name', label: 'Name'},
                    { key: 'surname', label: 'Surname'  },
                    { label: '' },
                    { label: '' },
                    { label: '' },
                    { key: 'btn', label: 'Chose examination'}]
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },
        init : function() {
                this.fields = [
                { key: 'name', label: 'Name' },
                { key: 'surname', label: 'Surname' },
                { key: 'email', label: 'Email' },
                { key: 'startDate', label: 'Start date'},
                { key: 'startTime',label: 'Start time'},
                { key: 'btn', label: 'Chose examination'}]
        },
        sortColumnA : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/pharmacist/sortResult',columnForSort, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.patients = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },
        sortColumnD : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/pharmacist/sortResultDescending',columnForSort, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.patients = response.data;
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        }
    }
}
</script>

<style scoped>
    .image_style{
        height: 400px;
        width: 60%;
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

    .space_style{
        margin-right:5px
    }
</style>