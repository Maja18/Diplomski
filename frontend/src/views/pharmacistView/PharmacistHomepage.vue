<template>
    <div id="pharmacistHomePage">
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
        <div style="width: 60%;
        height: 30%;
        margin-left: 300px;">
            <b-carousel
                id="carousel-fade"
                style="text-shadow: 0px 0px 2px #000"
                fade
                indicators
            >
                <b-carousel-slide
                img-src="https://image.freepik.com/free-vector/medicine-pharmacy-store-healthcare-industry-concept-flat-illustration-doctors-pharmacists-people-with-medical-drugs-vitamin-pills-capsules-online-pharmacy-drugstore-medicine-prescription_213110-153.jpg"
                ></b-carousel-slide>
                <b-carousel-slide
                img-src="https://static.vecteezy.com/system/resources/previews/001/871/342/large_2x/illustration-of-medicines-for-disease-and-vaccinations-flat-symbol-and-icon-for-drugs-health-pharmacy-can-be-used-for-landing-page-website-web-mobile-apps-flyer-banner-template-poster-free-vector.jpg"
                ></b-carousel-slide>
                <b-carousel-slide
                img-src ="https://image.freepik.com/free-vector/pharmaceutical-drug-products-manufactured-from-biological-sources-biopharmacology-products-biological-medical-product-natural-pharmacy-concept-pinkish-coral-bluevector-vector-isolated-illustration_335657-1603.jpg" 
                ></b-carousel-slide>
            </b-carousel>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PharmacistHomePage',
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