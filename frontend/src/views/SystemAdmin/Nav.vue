<template>
<nav class="navbar navbar-default" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">
    <img class="image_style space_style" style="width: 170px; height: 50px; left:10px;"
                src="@/images/benu.png">
  </div>
  <div id="navbar" class="navbar-menu">
    <div class="navbar-end">
      <div class="navbar-item">
        <div class="buttons" >
           <b-button pill variant="info"  href="http://localhost:8081/">
          <strong>Pocetna</strong>
           </b-button>
          <b-button pill variant="info"  href="/about">
            <strong>O nama</strong>
          </b-button>
            <b-button pill variant="info"  href="/promotions">
            <strong>Akcije i promocije</strong>
            </b-button>
           <b-button pill variant="info"  href="/showDrugs">
            <strong>Lekovi</strong>
           </b-button>
           <b-button pill variant="info"  href="/pharmacies">
            <strong>Apoteke</strong>
           </b-button>
          <b-button pill variant="outline-dark" style="margin-left:60px; margin-right:10px; width:150px " href="/registration">
            <strong>Registruj se</strong>
          </b-button>
           <b-button pill variant="outline-dark" style="width:150px"  href="/login">
            <strong>Uloguj se</strong>
          </b-button>
        </div>
      </div>
    </div>
  </div>
</nav>
</template>
<script>
export default {
    name: 'Nav'
}
</script>
<style scoped>
 nav {
    margin-top: 5px;
    margin-bottom: 5px;
    background-color: #17a2b8;
  } 
  .navbar a {
  text-align: center;
  padding: 15px;
  color: rgb(0, 0, 0);
  text-decoration: none;
  font-size: 17px;
}
.navbar a:hover {
  background-color: rgb(164, 198, 221);
  text-align:center;
}
  
</style>