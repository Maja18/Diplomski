<template>
    <div id="dermatologistAbsenceRequest">
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
                    <h3 class="ml-n mt-6 strong text_position" style="color: #17a2b8;"><b>Zahtev za odsustvo</b></h3>
                        <b-dropdown 
                        text="Izaberite tip odsustva:" 
                        variant="outline-info" 
                        style="width: 14%;
                        position: fixed;
                        left: 27%;
                        margin-top: 2%;" 
                        id="dropdown-divider">
                            <b-dropdown-item  
                            v-for="absence in absenceRequests" v-bind:key="absence"
                            @click="selectedType = absence">
                                {{ absence}}
                            </b-dropdown-item>
                        </b-dropdown>
                        <h5 
                        class="ml-n mt-6 strong text-left" 
                        style="margin-top:15%; margin-left:4%;">
                            <b>Izaberite početak:</b>
                        </h5>
                        <b-form-input 
                        type="date" 
                        class="object_space" 
                        v-model="startDate" 
                        filled 
                        placeholder="početni datum">
                        </b-form-input>
                        <h5 
                        class="ml-n mt-6 strong text-left" 
                        style="margin-top:4%; margin-left:4%;">
                            <b>Izaberite kraj:</b>                    
                        </h5>
                        <b-form-input 
                        type="date" 
                        class="object_space" 
                        v-model="endDate" 
                        filled 
                        placeholder="krajnji datum">
                        </b-form-input>
                            
                        <b-button pill variant="info" class = "btn btn-info btn-lg space_style" style=" width:200px; margin-top:30px" v-on:click = "sendRequest">Pošalji zahtev</b-button>
                    </div>
            </b-card>
        </div>
    </div>

</template>
absenceRequests
<script>
export default {
    name: 'DermatologistAbsenceRequest',
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
            this.axios.post('/absenceDermatologist/sendRequest',newAbsenceRequest, { 
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
        height: 73px;
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