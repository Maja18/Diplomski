<template>
    <div id="pharmacistDispensingDrugs">
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
        <h4 class="text-left"
        style="margin-left:2%;
        margin-top:1%;">
        <b> Search for registered drug:</b>
        </h4>
        <div
        style="margin-top: 2%;
        margin-left: 2%;">
            <b-form inline>
                <b-form-input
                type="number"
                v-model="codeForSearch"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="registration code"
                ></b-form-input>
                <b-button style="margin-left:2%;" v-on:click = "searchDispensing(codeForSearch)" variant="info">Search for reservation</b-button>
            </b-form>
            <div v-if="searched == 1">
             <b-card class="reservation" align="left">
                <b-row >
                    <b-col sm="5"> Patient </b-col>
                    <b-col>
                        <h5
                        text-align-left
                        v-bind:style="{ align: 'left', justify: 'left' }">
                            {{this.patient.patient.name}} {{this.patient.patient.surname}}
                        </h5>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col sm="5" > Due date </b-col>
                    <b-col>
                        <h5
                        text-align-left
                        v-bind:style="{ align: 'left', justify: 'left' }">
                        {{ format_date(this.patient.dueDate) }} 
                        </h5>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col left sm="5"> Reserved drug name</b-col>
                    <b-col>
                        <h5
                        text-align-left
                        v-bind:style="{ align: 'left', justify: 'left' }">
                        {{ this.patient.drugId.name }} 
                        </h5>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col sm="5"> Reservation status </b-col>
                    <b-col>
                        <h5
                        text-align-left
                        v-bind:style="{ align: 'left', justify: 'left' }">
                        {{ this.patient.drugStatus }} 
                        </h5>
                    </b-col>
                </b-row>
                <p class="text_align"><b> Confirm that patient took reserved drug</b></p>
                <b-button size="lg" variant="info"  v-on:click = "confirm" >  Confirm  </b-button>
            </b-card>
            </div>
        </div>
    </div>
</template>

<script>
import moment from 'moment'
export default {
    name: 'PharmacistDispensingDrugs',
    data() {
    return {
        drugReservation: [],
        startDate: '',
        endDate: '',
        patient: [{
            id: '',
            drugStatus: '',
            dueDate: '',
            patient:
                {id: '',
                name: "ana",
                surname: '',
                email: ''},
            pharmacyId: '',
            drugId:{
                name: '',
                }
            }
        ],
        searched: 0,
        codeForSearch : '',
        }
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
        searchDispensing : function(codeForSearch) {
            this.async;
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('/drugReservation/getReservation/'+codeForSearch,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
            }).then(response => {
                this.patient = response.data;
                this.searched = 1;
                if(response.data.id == null){
                    this.$bvToast.toast('Reservation ID is not valid!', {
                    variant: 'danger',
                    title: 'Drug reservation status',
                    solid: true
                    })
                }
            }).catch(res => {
                alert("Error");
                console.log(res);
            });

        },
        format_date(value){
         if (value) {
           return moment(String(value)).format('YYYY-MM-DD')
          }
        },
        confirm : async function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            
            const forUpdate = {
                id: this.patient.id,
            };
            this.axios.post('/drugReservation/updateReservation', forUpdate, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.patient.drugStatus = response.data;
                    this.$bvToast.toast('Reservation status updated!', {
                    variant: 'info',
                    title: 'Drug reservation status',
                    solid: true
                    })

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
    .object_space {
        width: 500px;
        margin-top: 1% !important;
        margin-left: 4% !important;
        margin-bottom: 2% !important;
    }
    .reservation {
        margin-top: 2% !important;
        background: #88c1ca; 
        width: 550px;
    }
    .text_align{
        margin-top: 3% !important;
    }
</style>