<template>
    <div id="pharmacistWorkCalendar">
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

        <!-- CALENDAR -->
            <div class='demo-app'>
            <div class='demo-app-main'>
            <FullCalendar
                class='demo-app-calendar'
                :options='calendarOptions'
            >
                <template v-slot:eventContent='arg'>
                <b>{{ arg.timeText }}</b>
                <i>{{ arg.event.title }}</i>
                </template>
            </FullCalendar>
            <b-modal ref="my-modal"  hide-footer title= "Counseling info:">
                <b-row>
                <b-col sm="3"> Patient </b-col>
                <b-col>
                    <h5
                    text-align-center
                    v-bind:style="{ align: 'center', justify: 'center' }"
                    >
                    {{ this.patientInfo }}
                    </h5>
                </b-col>
                </b-row>
                <b-row>
                <b-col sm="3"> Start time </b-col>
                <b-col>
                    <h5
                    text-align-center
                    v-bind:style="{ align: 'center', justify: 'center' }"
                    >
                    {{ format_date(this.startDate) }} at {{ format_time(this.startTime) }}
                    </h5>
                </b-col>
                </b-row>
                <b-row>
                <b-col sm="3"> Duration </b-col>
                <b-col>
                    <h5
                    text-align-center
                    v-bind:style="{ align: 'center', justify: 'center' }"
                    >
                    {{ this.duration }}
                    </h5>
                </b-col>
                </b-row>
                <b-row>
                <b-col>
                    <b-button
                    class="button_custom"
                    variant="info"
                    @click="hideModal"
                    >Close</b-button
                    >
                </b-col>
                <b-col> 
                   <router-link :to="{ name: 'PharmacistConsalting', params: {selectedCounseling: this.counselingId}}" class="search-btn">
                        <b-button
                        class="button_custom"
                        variant="info">
                        Start counseling
                        </b-button>
                    </router-link>
                </b-col>
                </b-row>
            </b-modal>
            </div>
        </div>
    </div>
</template>

<script>
import moment from 'moment'
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import timeGridPlugin from '@fullcalendar/timegrid'
import listPlugin from '@fullcalendar/list'
export default {
    name: 'PharmacistWorkCalendar',
    components: {
    FullCalendar // make the <FullCalendar> tag available
    },
    data() {
        return {
            calendarOptions: {
                plugins: [
                    dayGridPlugin,
                    timeGridPlugin,
                    listPlugin,
                    interactionPlugin, // needed for dateClick
                ],
                headerToolbar: {
                    left: 'prev today next',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                initialView: "dayGridMonth",
                editable: false,
                selectable: true,
                selectMirror: true,
                dayMaxEvents: true,
                weekends: true,
                select: this.handleDateSelect,
                eventClick: this.handleEventClick,
                eventsSet: this.handleEvents,
                events: this.scheduledExaminations
            },
            isAvailable: true,
            counselings: [],
            currentEvents: [],
            choosenCounseling : '',
            patientInfo: "",
            startDate: "",
            startTime: "",
            endTime: "",
            endDate: "",
            patientId: "",
            counselingId: "",
            definedCounselings: [],
            duration: "",
        };
        
    },
    mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/counseling/getCounselingsForCalendar' ,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
            }).then(response => {
                this.definedCounselings = response.data;
                let counselingsForCalendar = [];
                for (let i in response.data) {
                    let ex = response.data[i];
                    let startDate = new Date(ex.start); //ovde imamo datum
                    let startTime = new Date(ex.startTime);
                    startTime.setUTCDate(startDate.getDate());
                    startTime.setUTCMonth(startDate.getUTCMonth());
                    startTime.setFullYear(startDate.getFullYear());
                    console.log(startTime + "****" +startDate);
                    let newEvent = {
                        id: ex.id,
                        title: ex.pharmacyName +" "+ ex.patientInfo,
                        start: startTime,
                        backgroundColor: "#17a2b8",
                    };
                counselingsForCalendar.push(newEvent);
                }
                this.counselings = counselingsForCalendar;
                this.calendarOptions.events = counselingsForCalendar;
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
        },hideModal() {
            this.$refs["my-modal"].hide();
        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends
        },
        showDispensingDrugs : function(){
            window.location.href = "/pharmacistDispensingDrugs";
        },
        handleDateSelect(selectInfo) {
            let calendarApi = selectInfo.view.calendar;
            calendarApi.unselect();
            
        },
        handleEventClick(clickInfo) {
            this.$refs["my-modal"].show();
            
            let exam = this.definedCounselings.filter((obj) => {
                return obj.id == clickInfo.event.id;
            });
            this.pharmacyName = exam[0].pharmacyName;
            this.patientInfo = exam[0].patientInfo;
            this.startDate = new Date(exam[0].start);
            this.counselingId = exam[0].id;
            this.startTime = new Date(exam[0].startTime);
            this.endDate = new Date(this.startTime.getTime());
            this.startTime =  this.endDate.toLocaleString();
            this.duration = exam[0].duration;
            if (this.name === "") {
                this.isAvailable = false;
            } else {
                this.isAvailable = true;
            }
        
        },
        handleEvents(events) {
            this.currentEvents = events
        },
        format_date(value){
         if (value) {
           return moment(String(value)).format('YYYY-MM-DD')
          }
        },
        format_time(value){
         if (value) {
                return moment(String(value)).format('HH:mm')
            }
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

    .space_style{
        margin-right:5px
    }

    h2 {
        margin: 0;
        font-size: 16px;
    }

    ul {
        margin: 0;
        padding: 0 0 0 1.5em;
    }

    li {
        margin: 1.5em 0;
        padding: 0;
    }

    b { 
        margin-right: 3px;
    }

    .demo-app {
        display: flex;
        min-height: 100%;
        font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
        font-size: 14px;
    }

    .demo-app-sidebar {
        width: 300px;
        line-height: 1.5;
        background: #eaf9ff;
        border-right: 1px solid #d3e2e8;
    }

    .demo-app-sidebar-section {
        padding: 2em;
    }

    .demo-app-main {
        flex-grow: 1;
        padding: 3em;
    }

    .fc {
        max-width: 1100px;
        margin: 0 auto;
    }

    .button_custom {
        width: 210px;
        height: 50px;
        margin-top: 6%;
    }
</style>