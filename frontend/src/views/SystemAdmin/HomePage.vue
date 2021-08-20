<template>
<div id="homePage">
    <div class="homepage_style ">
            <span style="float: left; margin: 15px;">
                    <img class="image_style space_style" style="width: 50px; height: 50px; margin-right:10px;" src="@/images/natural-medicine.png">
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showHomePage">Home</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showProfile">Profile</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showPharmacyRegistration">Pharmacy registration</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showDermatologyRegistration">Dermatology registration</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showSupplierRegistration">Supplier registration</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showDrugManipulation">Drug manipulation</button>
                    <button class = "btn btn-info btn-lg space_style" v-on:click = "showComplaints">Complaints</button>
            </span>
              <span  style="float:right;margin:15px">
                    <button class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</button>
                </span>
        </div>
       
</div>   
</template>

<script>
export default {
    name: 'Profile',
    methods:{
        showHomePage: function(){
           window.location.href = "/homePage";
        },
        showDermatologyRegistration: function(){
           window.location.href = "/dermatologyRegistration";
        },
        showPharmacyRegistration: function(){
           window.location.href = "/pharmacyRegistration";
        },
        showSupplierRegistration: function(){
           window.location.href = "/supplierRegistration";
        },
         showProfile: function(){
           window.location.href = "/profileData";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
    }
}
</script>

<style scoped>
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
