<template>
    <div id="pharmacistConsalting">
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
        <div>
            <div style="left:0">
                <h4 class="text-left text_postion">Please choose one of the options to continue:</h4>
                <b-radio-group v-model="selected"  class="text-left text_postion">
                    <b-radio value="1" >Start counseling</b-radio>
                    <b-radio value="2" >Patient didn't come to counseling!</b-radio>
                </b-radio-group>
            </div>
            <b-tabs v-if="counselingID > 0" card>
                <b-tab title="Begin with examination" v-if="selected == 1" @click="getPatientValidDrugs">
                    <b-row class="mt-2">
                        <b-col sm="3">
                        <h3 for="textarea-large" class="text-left" style="font-size:18px">Enter information about examination:</h3>
                        
                        </b-col>
                        <b-col sm="12">
                        <b-form-textarea
                            id="textarea-large"
                            size="lg"
                            v-model="counselingInfo"
                            placeholder="type here.."
                        ></b-form-textarea>
                        </b-col>
                    </b-row>
                    <table v-if="isDrugAvailable==true" class="table table-striped" style="width:100%; border-bottom:solid;">
                            <thead class="thead-light">
                                <tr>
                                <th scope="col" 
                                v-for="f in fields" v-bind:key="f.key" >
                                    {{f.label}}
                                </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="drug in drugs" v-bind:key="drug.id">
                                <td>{{drug.name}}</td>
                                <td>{{drug.type}}</td>
                                <td>{{drug.drugForm}}</td>
                                <b-button 
                                variant="info" 
                                style="margin-top:1%;" 
                                v-on:click="getDrugSpecification(drug.id)" 
                                @click="selectedDrug = drug, isDrugChecked= false"
                                v-b-modal.modal-1>drug specification</b-button>
                                </tr>
                            </tbody>
                        </table>
                        <table v-else class="table table-striped" style="width:100%; border-bottom:solid;">
                            <thead class="thead-light">
                                <tr>
                                <h3>Alternative drugs for {{selectedDrug.name}}</h3>
                                </tr>
                            </thead>
                            <thead class="thead-light">
                                <tr>
                                <th scope="col" 
                                v-for="f in fields" v-bind:key="f.key" >
                                    {{f.label}}
                                </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="drug in alternativeDrugs" v-bind:key="drug.id">
                                <td>{{drug.name}}</td>
                                <td>{{drug.type}}</td>
                                <td>{{drug.drugForm}}</td>
                                <b-button 
                                variant="info" 
                                style="margin-top:1%;" 
                                v-on:click="getDrugSpecification(drug.id)" 
                                @click="selectedDrug = drug, isDrugChecked= false"
                                v-b-modal.modal-1>drug specification</b-button>
                                </tr>
                            </tbody>
                        </table>
                        <b-modal ref="modal-ref" id="modal-1" title="Drug specification" hide-footer>
                          <div class="drug_info">
                            <h4>Drug : <u>{{selectedDrug.name}}</u></h4>
                            <h4>Possible contra indication : <u>{{drugSpecification.contraIndications}}</u></h4>
                            <h4>Composition : <u>{{drugSpecification.composition}}</u></h4>
                            <h4>Manufacturer : <u>{{drugSpecification.manufacturer}}</u></h4>
                            <h4>Recommended terapy duration : <u>{{drugSpecification.therapyDuration}} days</u></h4>
                        </div>
                        <h4 style="margin-top:20px;">Length of therapy: </h4>
                        <b-form-input 
                        type="number" 
                        class="object_space" 
                        v-model="terapyDuration" 
                        filled 
                        placeholder="terapy duration"
                        style="margin-top:10px; font-size:16px;">
                        </b-form-input>
                        <h4 style="margin-top:20px;">Daily amount of therapy: </h4>
                        <b-form-input 
                        type="number" 
                        class="object_space" 
                        v-model="terapyAmount" 
                        filled 
                        placeholder="terapy duration"
                        style="margin-top:10px; font-size:16px;">
                        </b-form-input>
                        <b-row style="float: left; margin: 5px;">
                            <b-button
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; margin-right:5px; width:125px; height:50px;" 
                            v-on:click = "cancel">
                                Cancel
                            </b-button>
                            <b-button
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; margin-bottom:10px;width:320px;height:50px;" 
                            v-on:click="checkDrugAvailability">
                                Check drug availability
                            </b-button>
                        </b-row>
                            <b-button 
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; width:460px;height:50px;"
                            v-if="isDrugChecked == true"
                            v-on:click = "prescribe">
                                Prescribe drug
                            </b-button>
                            <b-button 
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; width:460px;height:50px;"
                            v-if="isDrugChecked == false"
                            @click="getAlternativeDrugs(); cancel()">
                                Check for alternative drugs
                            </b-button>
                        </b-modal>
                        <b-button 
                            v-if="updated == false"
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; width:460px;height:50px;"
                            v-on:click = "updateCounseling">
                                Finish counseling
                        </b-button>
                        <b-button v-if="updated == true"
                            class="btn btn-info btn-lg space_style" 
                            style="background-color:#17a2b8; width:460px;height:50px;"
                            v-on:click = "showNewConsalting">
                                Schedule new counseling
                        </b-button>
                </b-tab>
                <b-tab title="Unsustainable counsaling" v-else-if="selected == 2" >
                    <h3  class="text-left"
                    style="margin-left:1%;
                    margin-top:1%;">Please, confirm once more:</h3>
                    <b-form-checkbox
                    id="checkbox-1"
                    v-model="confirmation"
                    name="checkbox-1"
                    value="1"
                    size="lg"
                    unchecked-value="not_accepted"
                    class="text-left"
                    style="margin-left:28%;
                    margin-top:-2.5%;"
                    >
                    I confirm.
                    </b-form-checkbox>
                    <button 
                    class="button"
                    style="
                    margin-top:0%;
                    width: 20%;"
                    v-on:click = "patientDidntShow"> finish counseling</button>
                    
                </b-tab>
                <b-tab title="Please choose one of the options!" v-else disabled></b-tab>
            </b-tabs>
            <div v-else> 
                <h3>Scheduled counselings</h3>
                <table class="table table-striped" style="width:100%;">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col" 
                            v-for="f in fields2" 
                            v-bind:key="f.key" >
                                {{f.label}}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="c in scheduledCounselings" v-bind:key="c.id"
                        >
                        <td>{{c.patientInfo}}</td>
                        <td>{{format_date(c.startDate)}}</td>
                        <td>{{c.startTimeText}}</td>
                        <b-button variant="info" style="margin-top:1%;" v-on:click="startConsalting(c.counselingId)">start examination</b-button>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</template>

<script>
import moment from 'moment'
export default {
    name: 'PharmacistConsalting',
    data() {
      return {
        counselingID: '',
        selectedRowExamination: '',
        selected: 0,
        counseling: [],
        confirmation: 0,
        examinationInfo: "",
        fields: [
          { key: 'drugName', label: 'Drug name' },
          { key: 'type', label: 'Drug type' },
          { key: 'form', label: 'Drug form' },
          {label: 'Open specification'}
        ],
        fields2: [
          { key: 'patientInfo', label: 'Patient' },
          { key: 'startDate', label: 'Examination start date' },
          { key: 'startTime', label: 'Examination start time' },
          {label: 'Start examination'}
        ],
        drugs: [],
        drugSpecification: [],
        terapyDuration: '',
        terapyAmount: '',
        selectedDrug: [],
        isDrugChecked: null,
        alternativeDrugs: [],
        isDrugAvailable: true,
        updated: false,
        scheduledCounselings: [],

      }
    },
    mounted(){
        this.counselingID = this.$route.params.selectedCounseling;
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        if(this.counselingID >0){
        
        this.axios.get('/counseling/getCounselingById/'+this.$route.params.selectedCounseling,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.counseling = response.data;
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
        }else{
            this.axios.get('/counseling/getMyScheduledCounselings' ,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
            }).then(response => {
                this.scheduledCounselings = response.data;
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
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
        startConsalting: function(consaltingId){
            window.location.href = "/pharmacistConsalting/"+consaltingId;
        },
        showNewConsalting: function(){
            window.location.href = "/pharmacistNewConsalting";
        },
        showDispensingDrugs : function(){
            window.location.href = "/pharmacistDispensingDrugs";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        cancel() {
            this.$refs['modal-ref'].hide();
        }, 
        format_date(value){
         if (value) {
           return moment(String(value)).format('YYYY-MM-DD')
          }
        },
        patientDidntShow : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            
            const counselingInfo = {
                counselingId: Number(this.$route.params.selectedCounseling)
            };
            this.axios.post('/counseling/absenceRegister',counselingInfo, { 
                headers: {
                'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert(response.data);
                    this.$bvToast.toast('The patients absence was registered.', {
                    variant: 'warning',
                    title: 'INFO',
                    solid: true
                    })
                   
                })
                .catch(response => {
                    alert("Please, try agen.")
                    alert(response);
                })
        },getPatientValidDrugs: function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            const patientForSearch = {
                id: Number(this.counseling.patientInfo)
            };
            this.axios.post('/drug/getdrugsForPatient',patientForSearch ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                this.drugs = response.data;
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },getDrugSpecification: function(drugID) {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            this.axios.get('/drug/getOnlyDrugSpecification/'+drugID ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                this.drugSpecification = response.data;
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },prescribe :function(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        if(this.terapyDuration == ""){
                alert("Please enter terapy duration!")
                return;
            }
        if(this.terapyAmount == ""){
                alert("Please enter terapy amount!")
                return;
            }
            const prescribeDrug = {
                durationOfTherapy: this.terapyDuration,
                amountOfDrug: this.terapyAmount,
                pharmacyId: this.counseling.pharmacyID,
                patientId: Number(this.counseling.patientInfo),
                drugId: this.selectedDrug.id,

            };
            this.axios.post('/prescription/prescribe',prescribeDrug ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                    alert(response.data);
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },checkDrugAvailability: function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

            const checkDrug = {
                drugId: this.selectedDrug.id,
                pharmacyId: this.counseling.pharmacyID,
                patientId: this.counseling.patientInfo,
            };
            this.axios.post('/pharmacy/isDrugAvailable',checkDrug ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                this.isDrugChecked = response.data;
                this.$bvToast.toast('The checked drug availibility is '+response.data+' .', {
                    variant: 'info',
                    title: 'Check drug availability',
                    solid: true
                    })
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },getAlternativeDrugs: function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const alternativeForDrug = {
                drugId: this.selectedDrug.id,
                patientId: Number(this.counseling.patientInfo),

            };
            this.axios.post('/drug/getAlternativeDrugs',alternativeForDrug ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                this.alternativeDrugs = response.data;
                this.isDrugAvailable = this.alternativeDrugs.isDrugAvailable;
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },updateCounseling: function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const counselingInfo = {
                counselingId: this.counselingID,
                patientInfo: this.counselingInfo,

            };
            this.axios.post('/counseling/updateCounseling',counselingInfo ,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
            }).then(response => {
                alert(response.data);
                this.updated = true;
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
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
    .text_postion{
        margin-left:2%;
        margin-top:1%;
        margin-bottom:1%;
    }
    .button {
    display: inline-block;
    padding: 15px 25px;
    font-size: 24px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #17a2b8;
    border: none;
    border-radius: 15px;
    box-shadow: 0 9px #999;
    }

    .button:hover {background-color: #74b6c0}

    .button:active {
    background-color: #17a2b8;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
    }

    .drug_info {
        background: #b1ced3; 
        border-left-style: solid;
        border-color: #17a2b8;
        border-width: 8px;
    }

    .drug_info {
        background: #b1ced3; 
        border-left-style: solid;
        border-color: #17a2b8;
        border-width: 8px;
        padding-top:10px;
        padding-bottom:10px;
        padding-left:1px;
    }
</style>