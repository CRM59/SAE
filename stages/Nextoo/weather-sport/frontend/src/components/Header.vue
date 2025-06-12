<template>
  <Transition appear>
    <header>
      <p class="title">Nextoo</p>

      <div class="langages-selector">
        <button class="english-button" @click.prevent="$i18n.locale = 'en'"><img class="english-icon" src="../assets/img/english.png" alt="english"></button>
        <button class="french-button" @click.prevent="$i18n.locale = 'fr'"><img class="french-icon" src="../assets/img/french.png" alt="french"></button>
      </div>

      <div class="weather-infos">
        <img :src="currentConditionImage" alt="sun" class="weather-image">
        <div class="city-infos">
          <p>Roubaix</p>
          <p>{{ time }}</p>
        </div>
      </div>
    </header>
  </Transition>
</template>


<script setup>
import {onMounted, onUnmounted, ref} from "vue";


const currentConditionImage = ref("")

const time = ref(new Date().getUTCHours() + ":" + new Date().getUTCMinutes())

let interval

  onMounted(() => {
    fetch("http://localhost:8080/weather/city?cityName=Roubaix&nbDays=1")
        .then(response => {
          if(response.ok){
            return response.json();
          }
          throw new Error('Impossible de récupérer le json')
        })
        .then(data => currentConditionImage.value = data.currentCondition.weatherConditionIcon)



    interval = setInterval(() => {
      time.value = new Date().getHours() + ":" + new Date().getMinutes()
    },1000)
  })

onUnmounted(() => {
  clearInterval(interval)
})
</script>



<style scoped>

.langages-selector{
  display: flex;
  justify-content: center;
  align-items: center;
}

.english-button, .french-button{
  width: 70px;
  margin: 15px;
  background: none;
  border: none;
  cursor: pointer;
  transition: 0.5s;
}

.english-button:hover{
  filter: drop-shadow(0 0 10px red);
  width: 80px;
}

.french-button:hover{
  filter: drop-shadow(0 0 10px blue);
  width: 80px;
}

.english-icon, .french-icon{
  width: 100%;
}



.v-enter-active,
.v-leave-active {
  transition: opacity 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>



