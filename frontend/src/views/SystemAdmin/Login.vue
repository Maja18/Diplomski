

<template>
<div id="login">
     <div class="vue-tempalte">
            <div class="form-group">
                <label style="color:white">Email</label>
                <b-input-group size="lg">
                  <b-input-group-prepend is-text>
                  <b-icon icon="envelope"></b-icon>
                 </b-input-group-prepend>
                    <b-form-input type="email" placeholder="me@example.com" v-model="email" required></b-form-input>
                </b-input-group>
                <label style="color:white; margin-top:10px">Šifra</label>
                       <b-input-group size="lg">
      <b-input-group-prepend is-text>
        <b-icon icon="lock"></b-icon>
      </b-input-group-prepend>
      <b-form-input type="password" placeholder="me123" v-model="password" required></b-form-input>
    </b-input-group>
               
            </div>

            
            <b-button pill style="color:white" type="submit" class="button" v-on:click = "loginUser">Prijavi se</b-button>

    </div>
   <b-modal ref="my-modal" hide-footer scrollable title="Molim vas promenite šifru prilikom prvog logovanja" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                         <div class="form-group" style="margin-top:60px">
                <label style="color:white">Trenutna šifra</label>
                <b-input-group size="lg">
                  <b-input-group-prepend is-text>
                  <b-icon icon="lock"></b-icon>
                 </b-input-group-prepend>
                    <b-form-input type="password" v-model="currentPassword" required></b-form-input>
                </b-input-group>
                <label style="color:white">Nova šifra</label>
                <b-input-group size="lg">
                  <b-input-group-prepend is-text>
                  <b-icon icon="lock"></b-icon>
                 </b-input-group-prepend>
                    <b-form-input type="password" v-model="newPassword" required></b-form-input>
                </b-input-group>
                <label style="color:white">Potvrdite novu šifru</label>
                <b-input-group size="lg">
                  <b-input-group-prepend is-text>
                  <b-icon icon="lock"></b-icon>
                 </b-input-group-prepend>
                    <b-form-input type="password" v-model="newPasswordRepeat" required></b-form-input>
                </b-input-group>
            </div>
                        <b-button style="width:160px; margin-left:290px; margin-top:-50px" pill variant="info" @click= "changePassword($event)" class="btn btn-primary">Potvrdi</b-button>        
                    </div>                
                </div>
            </div>
        </b-modal>
</div>   
</template>

<script>
export default {
    name: 'Login',
    data(){
        return{
            email:'',
            password:'',
            currentPassword : "",
            newPassword : "",
            newPasswordRepeat : "",
            authority :""  ,
            
        }
    },
    methods:{
         showProfile: function(){
           window.location.href = "/profileData";
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
        loginUser : function() {
            const loginInfo ={
                email : this.email,
                password : this.password,
            }
            localStorage.removeItem('token');

            this.axios.post('/auth/login',loginInfo)
                .then(response => {
                    localStorage.setItem('token', JSON.stringify(response.data.accessToken));
                    console.log(response.data.token);
                    
                    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                    this.axios.get('/auth/authority',{  
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }
                    }).then(response => {
                        
                            this.authority = response.data.authorities[0].authority;
                            
                            if(this.authority==="ROLE_PATIENT") 
                             if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/profileInfo';
                                }
                        
                            else if(this.authority==="ROLE_DERMATOLOGIST")
                                if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/dermatologistProfile';
                                }
                                
                            else if(this.authority==="ROLE_SUPPLIER")
                                if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                    alert("Molim vas promenite šifru prilikom prvog logovanja!"); 
                                }
                                else {
                                    window.location.href = '/profileDataSupplier';
                                    alert("Success!"); 
                                }
                            else if(this.authority==="ROLE_SYSTEM_ADMINISTRATOR")
                                if(response.data.firstLogged) {
                                     this.$refs['my-modal'].show();
                                     alert("Molim vas promenite šifru prilikom prvog logovanja!"); 
                                }
                                else {
                                     window.location.href = '/profileData';
                                     alert("Success!"); 

                                }
                            else if(this.authority==="ROLE_PHARMACY_ADMINISTRATOR")
                                if(response.data.firstLogged) {
                                       this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/pharmacyAdminProfile';   
                                }
                            else if(this.authority==="ROLE_PHARMACIST")
                             if(response.data.firstLogged) {
                                    this.$refs['my-modal'].show();
                                }
                                else {
                                    window.location.href = '/pharmacistHomePage';
                                }
                               
                            else alert("Error has occured."); 

                    }).catch(res => {
                                alert("NOT OK");
                                // window.location.href = '/patientProfile';
                                //window.location.href = '/dermatologistHomepage';
                                //window.location.href = '/profileDataSupplier';
                                 
                                    console.log(res);
                            });

                            })
                .catch(response => {
                       alert("Molim vas unesite validne podatke!");
                        console.log(response);
                 });   
        },
    changePassword : function() {

        if(this.newPassword != this.newPasswordRepeat) {
            alert("Nove šifre moraju biti jednake!")
            return;
        }

        const changePasswordInfo ={
                oldPassword : this.currentPassword,
                newPassword : this.newPassword,
                rewriteNewPassword : this.newPasswordRepeat,
                email : this.email,
            } 
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.post('/auth/passwordFirstLogin',changePasswordInfo,{ 
                         headers: {
                                'Authorization': 'Bearer ' + token,
                }}).then(response => {
                    console.log(response);
                    if(this.authority==="ROLE_SYSTEM_ADMINISTRATOR")
                        window.location.href = '/profileData';
                    else if(this.authority==="ROLE_PATIENT") 
                        window.location.href = '/profileInfo';
                    else if(this.authority==="ROLE_DERMATOLOGIST")
                       window.location.href = '/dermatologistProfile';
                    else if(this.authority==="ROLE_SUPPLIER")
                        window.location.href = '/profileDataSupplier';
                    else if(this.authority==="ROLE_PHARMACY_ADMIN")
                        window.location.href = '/pharmacyAdminProfile';   
                    else if(this.authority==="ROLE_PHARMACIST")
                         window.location.href = '/pharmacistHomepage';
                    else alert("Error has occured."); 
                }).catch(res => {
                    alert(res.response.data.message);
                });
    }

      }
    
}
</script>

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f6f9fa!important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
    }

.form-group{
  position: relative;
  z-index: 1;
  background:  #174452;
  max-width: 400px;
  margin: 0 auto 100px;
  padding: 65px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  
    }
.button {
   background:  #174452;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(25, 40, 90); 
   padding: 10px 20px;
   position: absolute;
   top: 80%;
   right: 40%;
   width: 20%;
   height: 7%;
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
