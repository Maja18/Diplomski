<template>
    <div id="pharmacistAbsenceRequest">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" style="width: 50px; height: 50px; margin-right:10px;" src="@/images/natural-medicine.png">
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showHomepage">Home</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profile</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showPatients">My patients</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showWorkCalendar">Work calendar</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showAbsenceRequest">Create a vacation</button>
                <button class = "btn btn-info btn-lg space_style" v-on:click = "showConsalting">Consaltings</button>
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
                top: 9.5%;
                width: 100%;
                height: 100%;
                max-width: 100%;"
                >
                    <div style="position: fixed;
                    width:44%;
                    height:60%; 
                    border:2px solid #17a2b8; 
                    background-color: #ffffff; 
                    left:25%;
                    top: 18.5%;">
                    <h3 class="ml-n mt-6 strong text_position" style="color: #17a2b8;"><b>Absence request</b></h3>
                        <b-dropdown 
                        text="Select absence type:" 
                        variant="outline-info" 
                        style="width: 14%;
                        position: fixed;
                        left: 27%;
                        margin-top: 1%;" 
                        id="dropdown-divider">
                            <b-dropdown-item  
                            v-for="absence in absenceRequests" v-bind:key="absence"
                            @click="selectedType = absence">
                                {{ absence}}
                            </b-dropdown-item>
                        </b-dropdown>
                        <h5 
                        class="ml-n mt-6 strong text-left" 
                        style="margin-top:12%; margin-left:4%;">
                            <b>Select start date:</b>
                        </h5>
                        <b-form-input 
                        type="date" 
                        class="object_space" 
                        v-model="startDate" 
                        filled 
                        placeholder="choose start date">
                        </b-form-input>
                        <h5 
                        class="ml-n mt-6 strong text-left" 
                        style="margin-top:2%; margin-left:4%;">
                            <b>Select end date:</b>                    
                        </h5>
                        <b-form-input 
                        type="date" 
                        class="object_space" 
                        v-model="endDate" 
                        filled 
                        placeholder="choose end date">
                        </b-form-input>
                            
                        <b-button class="btn btn-info btn-lg object_space" style="background-color:#17a2b8; width:500px;" v-on:click = "sendRequest">Send request</b-button>
                    </div>
            </b-card>
    </div>
</template>

<script>
export default {
    name: 'PharmacistAbsenceRequest',
    data() {
    return {
        selectedType: "",
        absenceRequests: [],
        startDate: '',
        endDate: '',
        }
    },
    mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/absenceDermatologist/absenceType',{ 
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        }).then(response => {
            this.absenceRequests = response.data;
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
        showConsalting: function(){
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
        sendRequest: function() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            if(this.startDate == null){
                alert("Please choose start date for your absence request.")
                return;
            }
            if(this.endDate == null){
                alert("Please choose end date for your absence request.")
                return;
            }
            if(this.selectedType == ''){
                alert("Please select vacatyon type.")
                return;
            }

            const newAbsenceRequest = {
                startDate: this.startDate,
                endDate: this.endDate,
                vacationType: this.selectedType
            };
            this.axios.post('/absencePharmacist/sendRequest',newAbsenceRequest, { 
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
    .object_space {
        width: 500px;
        margin-top: 1% !important;
        margin-left: 4% !important;
        margin-bottom: 2% !important;
    }
</style>