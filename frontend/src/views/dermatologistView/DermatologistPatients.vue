<template>
    <div id="dermatologistPatients">
        <div class="homepage_style ">
          <span style="float: left; margin: 15px">
                <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
                <b-button style="margin-left:20px" pill class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profil</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">Pacijenti</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Radni kalendar</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Odmor</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showExaminations">Pregledi</b-button>
                <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "showNewExamination">Nov pregled</b-button>
            </span>
            <span  style="float:right;margin:15px">
             <b-button pill class = "btn btn-info btn-lg space_style" v-on:click = "logOut">
                     <b-icon icon="power"   aria-hidden="true"></b-icon> Odjavi se
            </b-button>
            </span>
        </div>
        <h3 class="text-left"
        style="margin-left:4%;
        margin-top:2%;">
        Pretraga pacijenta:
        </h3>
        <div
        style="margin-top: 2%;
        margin-left: 4%;">
            <b-form inline>
                <b-form-input
                v-model="nameForSerch"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Ime pacijenta"
                ></b-form-input>
                <b-form-input 
                v-model="surnameForSearch"
                placeholder="Prezime pacijenta">
                </b-form-input>
                <b-button pill variant="info" style="margin-left:2%; width:120px" v-on:click = "searchPatient">Traži</b-button>
            </b-form>
        </div>  
        <div style="height:25px; margin-top:10px"></div>
            <h3>Pacijenti</h3>
            <table class="table table-striped" style="width:92%;margin-left:57px;margin-top:30px">
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
                    @click="selectedExamination = patient.examinationID">
                    <td>{{patient.name}}</td>
                    <td>{{patient.surname}}</td>
                    <td>{{patient.email}}</td>
                    <td>{{patient.startDate}}</td>
                    <td>{{patient.startTime}}</td>
                    <router-link :to="{ name: 'DermatologistExamination', params: {selectedExamination: patient.examinationID}}" class="search-btn">
                        <b-button  pill class="btn btn-primary" variant="info" style="margin-top:1%;color:#2F4F4F">započni pregled</b-button>
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
    name: 'DermatologistPatients',
    data() {
      return {
        sortBy: 'age',
        sortDesc: false,
        fields: [
          { key: 'name', label: 'Ime' },
          { key: 'surname', label: 'Prezime' },
          { key: 'email', label: 'Email' },
          { key: 'startDate', label: 'Početak'},
          { key: 'startTime',label: 'Kraj'},
          { key: 'btn', label: ''}
        ],
        selectMode: 'single',
        patients: [],
        name: "",
        surname: "",
        email: "",
        nameForSerch: "",
        surnameForSearch: "",
        selected: [],
        selectedExamination: '',
        fieldForSorting: "name",
        searche: 0,
        
      }
    },
    mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/dermatologist/myPatients',{ 
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
           window.location.href = "/dermatologistHomepage";
        },
        showProfile: function(){
           window.location.href = "/dermatologistProfile";
        },
        showPatients: function(){
            window.location.href = "/dermatologistPatients";
        },
        showWorkCalendar: function(){
            window.location.href = "/dermatologistWorkCalendar";
        },
        showAbsenceRequest: function(){
            window.location.href = "/dermatologistAbsenceRequest";
        },
        showExaminations: function(){
            window.location.href = "/dermatologistExamination/-1";
        },
        showNewExamination: function(){
            window.location.href = "/dermatologistNewExamination";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
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
            this.axios.post('/dermatologist/searchMyPatient',patientForSearch, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.searched = 1;
                    this.patients = response.data;
                    this.fields = [
                        { key: 'name', label: 'Name' },
                        { key: 'surname', label: 'Surname' },
                        { label: '' },
                        { label: '' },
                        { label: '' },
                        { key: 'btn', label: 'Start examination' }
                    ];
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },
        sortColumnA : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/dermatologist/sortResult',columnForSort, { 
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
        },sortColumnD : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.fieldForSorting == 'btn'){
                return;
            }
            const columnForSort = {
                sortingKey : this.fieldForSorting
            };
            this.axios.post('/dermatologist/sortResultDescending ',columnForSort, { 
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
        height: 73px;
    }

    .dermatologistPatients{
        margin-left:50px;
        margin-right:50px;
    }

    .space_style{
        margin-right:5px
    }
    .th_header {
        display: table-cell;
        -ms-text-combine-horizontal: inherit;
        font-weight: bold;
        text-align: center;
        }
</style>