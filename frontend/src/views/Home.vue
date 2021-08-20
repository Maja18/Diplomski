<template>
  <div class="home">
    <section class="hero is-dark">
    <img class="image_style space_style" style="width: 300px; height: 300px; left:10px;"
                src="@/images/ptica.png">
  </section>
  <section class="hero is-dark">
   <h2 style="margin-top:-20px">Dobrodosli u Benu sistem apoteka</h2>
      <h4 style=" margin-top:40px; width: 158px;">NAŠ BLOG</h4> 
   </section>
<div style="margin-top:-180px; margin-left:0px;">
  <b-card
    title="Nega lica i tela"
    img-src="http://www.negujse.rs/uploads/news/thumb_1585125402~~maske-za-kucnu-negu-neguj-se-blog.jpg"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 25rem;"
    class="mb-2"
  >
    <b-card-text style="text-align: left;">
      Kao što imate neku rutinu u nezi lica, tako treba da stvorite sebi i rutinu u nezi kože tela. Znam da vam je bilo teško da pronađete odgovarajuće sredstvo za čišćenje lica, piling i hidrantnu kremu...
    </b-card-text>

    <b-button pill href="/bodyCare" variant="info">Saznaj više</b-button>
  </b-card>
</div>
<div style="margin-top:-527px ;margin-left:527px">
  <b-card
    title="Vitamini i minerali"
    img-src="https://static.insiderenvy.com/images/2020/01/09105906/0118.Plant-Based-Diet-Blog-Hero-730x480.jpg"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 25rem;"
    class="mb-2"
  >
    <b-card-text style="text-align: left;">
     Vitamini su organska jedinjenja prirodno zastupljena u hrani, koja su potrebna za pravilan rast i razvoj, kao i za pravilno funkcionisanje organizma.U slučaju da postoji nedostatak vitamina...
    </b-card-text>

    <b-button pill href="/vitamins" variant="info">Saznaj više</b-button>
  </b-card>
</div>

<div style="margin-top:-526px ;margin-left:1060px">
  <b-card
    title="Kako pravilno čuvati lekove"
    img-src="data:image/jpeg;base64,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"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 25rem;"
    class="mb-2"
  >
    <b-card-text style="text-align: left;">
      Ukoliko je to moguće, lekove čuvajte na jednom mestu. To je dobro iz više razloga – pre svega imate uvid u ono što vam je potrebno i znate koje lekove bi trebalo ponovo da nabavite. Tako nećete ulaziti u bespotrebnu nabavku...
    </b-card-text>

    <b-button pill href="/drugAdvices" variant="info">Saznaj više</b-button>
  </b-card>
</div>
<div class="image">
</div>
  </div>
</template>

<script>
// @ is an alias to /src
//import DermatologistHomepage from '../views/dermatologistView/DermatologistHomepage.vue'


export default {
  name: 'Home',
  components: {
    //DermatologistHomepage
  }
}
</script>
<style  scoped>
div.image {
  margin-block-start: 100px;
   background-image:url("../images/skrin.png");
  background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: 650px;
  
}
.hero {    
    text-align: center;
    
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: 300px;
  }
  .hero-body .title {
    text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.6);
    padding: 420px 0 20px 0;
    font-size: 60px;
    
  }
  .subtitle {
    font-size: 30px;
  }
    .welcome {
      width: 400px;
      padding: 10px;
      margin-left: auto;
      margin-right: auto;
    }
  
  .is-xl {
    font-size: 1.7rem;
  }
  .home{
    margin-left: 50px;
  margin-right: 50px;
  }
</style>
