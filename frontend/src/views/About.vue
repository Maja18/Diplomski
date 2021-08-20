<template>
  <div class="about">
    <div >
    <h3 style=" margin-top:20px">BENU - NEGUJTE DOBRE NAVIKE</h3>
    <b-card img-src="https://thumbs.dreamstime.com/b/two-natural-medical-pills-green-leaves-pharmaceutical-vector-symbol-leaf-pharmastore-homeopathic-alternative-medicine-191931478.jpg" img-alt="Card image" img-left  class="text-left" style="height:300px">
      <b-card-text>
        <p>Prisutni smo na 400 lokacija širom naše zemlje i biće nas još. 
        Apotekarska ustanova BENU je najveći lanac apoteka u Srbiji i deo je velike međunarodne kompanije PHOENIX iz Nemačke.
        Ono po čemu se BENU izdvaja na tržištu jeste konstantan rad sa zaposlenim farmaceutima na usavršavanju i upoznavanju najnovijih dostignuća medicine i farmakologije, sa ciljem da se pacijentima ponudi najbolji mogući savet. 
        BENU u svom poslovanju primenjuje najsavremenije evropske standarde u nabavci, distribuciji, skladištenju i izlaganju lekova i lekovitih sredstava, sve sa ciljem maksimalne bezbednosti za pacijente.
        </p>
        <p>
          U svojoj ponudi, BENU poseduje i liniju proizvoda pomoćnih lekovitih sredstava LIVSANE vrhunskog kvaliteta,
          uglavnom proizvedenih u Nemačkoj. Svakog meseca, u saradnji sa renomiranim farmaceutskim i kozmetičkim kompanijama, 
          organizujemo brojne akcije pomoću kojih predstavljamo naš asortiman.
        </p>
      </b-card-text>
    </b-card>
    <h3 style=" margin-top:20px">MITOLOŠKA PTICA KAO ZVEZDA VODILJA</h3>
    <b-card img-src="data:image/png;base64,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" img-alt="Card image" img-right  class="text-left" style="height:250px">
      <b-card-text>
        <p>
          Mitološka ptica Benu, simbol je koji dominira logom BENU apoteka. Potekla je iz mitologije drevnog Egipta, 
          a po verovanju starih Egipćana, Sunce se prvi put pojavilo u liku ptice Benu. BENU je simbol života, energije, 
          preporoda i regeneracije. Ona je i vesnik sreće i pozitivne energije, baš kao što je i naš cilj: čvrste veze sa 
          našim partnerima, pacijentima i dobavljačima, aktivno učešće u misiji zdravlja i dobrobiti zajednice i čovekove 
          okoline.Mitološka ptica Benu, simbol je koji dominira logom BENU apoteka. Potekla je iz mitologije drevnog Egipta, 
          a po verovanju starih Egipćana, Sunce se prvi put pojavilo u liku ptice Benu. BENU je simbol života, energije, 
          preporoda i regeneracije. Ona je i vesnik sreće i pozitivne energije, baš kao što je i naš cilj: čvrste veze sa
           našim partnerima, pacijentima i dobavljačima, aktivno učešće u misiji zdravlja i dobrobiti zajednice i čovekove 
           okoline.
        </p>
      </b-card-text>
    </b-card>
     <h3 style=" margin-top:20px">FILOZOFIJA 5 ČULA - 5 VREDNOSTI BENU BRENDA</h3>
    <b-card img-src="https://images1-fabric.practo.com/practices/1186661/dr-v-p-singh-clinic-noida-5ad6d94d62648.jpg" img-alt="Card image" img-left  class="text-left" style="height:690px;">
      <b-card-text>
        <p>Filozofija 5 vrednosti BENU brenda čini suštinu naše farmaceutske usluge. Baš kao što našim navikama upravlja 5 naših čula, 5 vrednosti BENU brenda rukovode našom uslugom.
        </p>
        <h5>KOMPETENTNOST</h5>
        <p>
          Naše stručno osoblje daje našim klijentima savete o proizvodima i njihovom uticaju na sve aspekte zdravlja. Trudimo se da svojim kupcima približimo BENU apoteku i njene zaposlene kao deo porodice koja brine o njima. Doslednost i pouzdanost su naš imperativ, a proizvodi koje nudimo su strogo kontrolisani i vrhunskog kvaliteta.
        </p>
        <h5>LJUBAZNOST</h5>
        <p>
         Ljubaznost i prijateljsko ophođenje su osobine koje krase naše zaposlene, a dvosmerna komunikacija je osnov farmaceutske usluge. Uz to, trudimo se da osećaj da se naši klijenti nalaze u dobrim rukama i u prijateljskom okruženju nikada ne izbledi.
        </p>
        <h5>SVEŽINA</h5>
        <p>
         Trudimo se da, od prvog koraka u apoteci, naš klijent oseti živu energiju i vitalnost. Proaktivan i pozitivan pristup su neizostavan deo naših saveta. Kreiramo dobar osećaj i enterijerom naše apoteke pružamo mogućnost jedinstvenog iskustva u korišćenju i istraživanju naših proizvoda. Verujemo da jedino tako možemo doprineti dobrom raspoloženju naših klijenata i njihovoj veri u neophodnost preventive i negovanja zdravih životnih navika.
        </p>
        <h5>INOVATIVNOST</h5>
        <p>
         Verujemo u inovativan pristup potrebama klijenata i primenu novih medicinskih saveta i preventive. Svesni da društvo sve više pažnje pridaje negovanju zdravih životnih navika dajemo svoj doprinos kroz edukaciju klijenata i odgovore i na njihova nepostavljena pitanja. Budućnost pripada inovativnim kompanijama, a BENU apoteke su u tome lideri. To pokazujemo u svakom segmentu naše usluge i milimetru prostora.
        </p>
        <h5>POSVEĆENOST</h5>
        <p>
          Već pri prvom kontaktu se trudimo da prepoznamo potrebe naših klijenata i odgovorimo na njih. Briga o našim klijentima nam je uvek u fokusu, a kreativnim pristupom se trudimo da svakom od njih pružimo osećaj jedinstvenosti i posebnosti. U osnovi naše posvećenosti leži timski duh naših zaposlenih i kreiranje dobrobiti svih naših klijenata.
        </p>
      </b-card-text>
    </b-card>
    </div>
  </div>
</template>

<style scoped>
.about{
  margin-left: 50px;
  margin-right: 50px;
}
</style>

