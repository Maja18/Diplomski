<!-- https://fullcalendar.io/docs/vue - link pomoc kog je odradjena zadatak i fullcalendar-->
<template>
    <div id="dermatologistWorkCalendar">
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
            <div style="padding-top:10px;
        padding-bottom:10px;
        padding-left:1px;
        background: #white">
            <h4 style="margin-left:4%;margin-top:8px" class="text-left">Molim vas izaberite željenu apoteku:</h4>
            <b-dropdown text="Izaberite apoteku" dropright  variant="outline-info" style="width: 14%;
                position: fixed;
                left: 45%;
                margin-top: -2%;">
                <b-dropdown-item  
                    v-for="pharm in pharmacies" v-bind:key="pharm.id" :value="pharm.id"
                    @click="filter(pharm.name, definedExaminations)">
                    {{pharm.name}}
                </b-dropdown-item>
            </b-dropdown>
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
            <b-modal ref="my-modal"  hide-footer title= "Examination info:">
                <b-row text-align-center>
                <b-col sm="3"> Pharmacy </b-col>
                <b-col>
                    <h5
                    text-align-center
                    v-bind:style="{ align: 'center', justify: 'center' }"
                    >
                    {{ this.pharmacyName }}
                    </h5>
                </b-col>
                </b-row>
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
                <b-col v-if="startExamination==true"> 
                    <router-link :to="{ name: 'DermatologistExamination', params: {selectedExamination: this.examinationId}}" class="search-btn">
                        <b-button
                        class="button_custom"
                        variant="info">
                        Start examination
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
    name: 'DermatologistWorkCalendar',
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
            startExamination: true,
            examinations: [],
            currentEvents: [],
            choosenExamination : '',
            patientInfo: "",
            pharmacyName: "",
            startDate: "",
            startTime: "",
            endTime: "",
            endDate: "",
            patientId: "",
            examinationId: "",
            definedExaminations: [],
            name: "",
            surname: "",
            duration: "",
            pharmacies: [],
        };
        
    },
    mounted() {
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('/pharmacy/pharmacystaff/dermatologist',{ 
            headers: {
                'Authorization': 'Bearer ' + token,
            }
        }).then(response => {
            this.pharmacies = response.data;
        }).catch(res => {
            alert("Error");
            console.log(res);
        });
        this.axios.get('/examination/getExaminationsForCalendar' ,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
            }).then(response => {
                this.definedExaminations = response.data;
                let examinationsForCalendar = [];
                for (let i in response.data) {
                    let ex = response.data[i];
                    let startDate = new Date(ex.start); //ovde imamo datum
                    let startTime = new Date(ex.startTime);
                    startTime.setUTCDate(startDate.getDate());
                    startTime.setUTCMonth(startDate.getUTCMonth());
                    startTime.setFullYear(startDate.getFullYear());
                    if(ex.patientInfo == null){
                        let newEvent = {
                            id: ex.id,
                            title: ex.pharmacyName,
                            start: startTime,
                            backgroundColor: "#17a2b8",
                            };   
                        examinationsForCalendar.push(newEvent);
                    }else{
                            let newEvent = {
                            id: ex.id,
                            title: ex.pharmacyName +" "+ ex.patientInfo,
                            start: startTime,
                            backgroundColor: "#17a2b8",
                        };
                        examinationsForCalendar.push(newEvent);
                    }
                }
                this.examinations = examinationsForCalendar;
                this.calendarOptions.events = examinationsForCalendar;
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
        hideModal() {
            this.$refs["my-modal"].hide();
        },
        handleWeekendsToggle() {
            this.calendarOptions.weekends = !this.calendarOptions.weekends
        },
        handleDateSelect(selectInfo) {
            let calendarApi = selectInfo.view.calendar;
            calendarApi.unselect();
            
        },
        handleEventClick(clickInfo) {
            this.$refs["my-modal"].show();
            
            let exam = this.definedExaminations.filter((obj) => {
                return obj.id == clickInfo.event.id;
            });
            this.pharmacyName = exam[0].pharmacyName;
            this.patientInfo = exam[0].patientInfo;
            this.startDate = new Date(exam[0].start);
            this.examinationId = exam[0].id;
            this.startTime = new Date(exam[0].startTime);
            this.endDate = new Date(this.startTime.getTime());
            this.startTime =  this.endDate.toLocaleString();
            this.duration = exam[0].duration;
            if (this.patientInfo == null) {
                this.startExamination = false;
            } else {
                this.startExamination = true;
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
        filter: function(pharmacyName, examiantions) {
            let examinationsForCalendar = [];
                for (let i in examiantions) {
                    let ex = examiantions[i];
                    if(ex.pharmacyName == pharmacyName){
                        let startDate = new Date(ex.start);
                        let startTime = new Date(ex.startTime);
                        startTime.setUTCDate(startDate.getDate());
                        startTime.setUTCMonth(startDate.getUTCMonth());
                        startTime.setFullYear(startDate.getFullYear());
                        let newEvent = {
                            id: ex.id,
                            title: ex.pharmacyName +" "+ ex.patientInfo,
                            start: startTime,
                            backgroundColor: "#17a2b8",
                        };
                    examinationsForCalendar.push(newEvent);
                }
                this.examinations = examinationsForCalendar;
                this.calendarOptions.events = examinationsForCalendar;
                }
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