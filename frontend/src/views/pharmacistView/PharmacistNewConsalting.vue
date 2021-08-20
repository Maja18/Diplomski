<template>
    <div id="pharmacistNewConsalting">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 50px; height: 50px; margin-right:10px;" src="@/images/natural-medicine.png">
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showHomepage">Home</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profile</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">My patients</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Work calendar</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Create a vacation</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showConsaltings">Consaltings</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showNewConsalting">Schedule new consalting</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showDispensingDrugs">Dispensing drugs</button>
            </span>
            <span  style="float:right;margin:15px">
                <button class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
            </span>
        </div>
        <b-card 
        style=" background: #e6f9fc; 
        position: fixed;
        top: 10.5%;
        width: 100%;
        height: 100%;
        max-width: 100%;"
        >
            <div style="position: fixed;
            width:44%;
            height:60%; 
            border:2px solid #17a2b8; 
            background-color: #ffffff; 
            left:2%;
            top: 18.5%;">
            <h4 class="ml-n mt-6 strong text_position" style="color: #17a2b8;"><b>Schedule new counseling</b></h4>
            <div style="position: fixed;
                    top: 18%; 
                    max-width: 100%;
                    left: 50px;
                    height: 400px;
                    width: 40%;
                    max-width: 100%;">
                    <h5 class="ml-n mt-6 strong text-left" style="margin-bottom:8%; margin-top: 2% !important;"><b> Search or chooese patient from list:</b></h5>
                    <input list="my-list-id" v-model="selectedPatient" 
                    style=" width: 14%;
                    border: 3px solid #17a2b8;
                    position: fixed;
                    left: 3.5%;
                    top: 22%;
                    margin-top: 1% !important;
                    margin-bottom: 1% !important;
                    height: 5%;" 
                    placeholder="      Enter patient">
                    <datalist id="my-list-id">
                        <option v-for="patient in patients" v-bind:key="patient.id">
                            {{ patient.name }} {{patient.surname}} | {{patient.email}} 
                        </option>
                    </datalist>
                    <h5 class="ml-n mt-6 strong text-left" style="margin-top:10%"><b>Select date of counseling</b></h5>
                    <b-form-input type="date" class="object_space" v-model="startDate" filled placeholder="Enter date of counseling"></b-form-input>
                    <h5 class="ml-n mt-6 strong text-left" ><b>Select start time of counseling</b></h5>
                    <b-form-input type="time" class="object_space" v-model="startTime" filled placeholder="Start time of counseling"></b-form-input>
                    <h5 class="ml-n mt-6 strong text-left"><b>Select end time of counseling</b></h5>
                    <b-form-input type="time" class="object_space" v-model="endTime" filled placeholder="End time of counseling"></b-form-input>
                    
                    <b-button class="btn btn-info btn-lg space_style object_space" style="background-color:#17a2b8; width:18cm;" v-on:click = "scheduleCounseling">Schedule</b-button>
            </div>
        </div>
        </b-card>
        </div>
</template>

<script>
export default {
    name: 'PharmacistNewConsalting',
    data() {
      return{
        patients:[''],
        selectedPatient: [''],
        startDate: null,
        startTime: null,
        endTime: null,
      }  
    },
    mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/patient/allPatients',{ 
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
        showConsaltings: function(){
            window.location.href = "/pharmacistConsalting/-1";
        },
        showNewConsalting: function(){
            window.location.href = "/pharmacistNewConsalting";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        }, 
        showDispensingDrugs : function(){
            window.location.href = "/pharmacistDispensingDrugs";
        },
        scheduleCounseling : function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.selectedPatient == ""){
                alert("Please choose patient for examination.")
                return;
            }
            if(this.startDate == null){
                alert("Please choose date for examination.")
                return;
            }
            if(this.startTime == null){
                alert("Please choose start time for examination.")
                return;
            }
            if(this.endTime == null){
                alert("Please choose end time for examination.")
                return;
            }
            
            const newCounseling = {
                startDate: this.startDate,
                startTime : this.startTime,
                endTime: this.endTime,
                patientInfo: this.selectedPatient
            };
            this.axios.post('/counseling/schedule',newCounseling, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert(response.data)
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    alert(response);
                })
        },
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
    .text_position{
        position: fixed;
        top: 11%;
        margin-left: 1%;
        margin-top: 1% !important;
        margin-bottom: 1% !important;
        height: 5%;
    }
    .space_style{
        margin-right:5px;
        max-width: 100%;
    }

    .object_space {
        margin-top: 2% !important;
        margin-bottom: 2% !important;
    }
</style>