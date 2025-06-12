<template>
  <Transition appear>
  <form action="" class="sport-form add-form">
    <h1 class="title">{{ $t('labels.newSport') }}</h1>

    <div class="sport-name-selector text-selector">
      <label class="sport-name-label" for="sport-name-input">{{ $t('labels.name') }}</label>
      <input type="text" name="sportName" id="sport-name-input" class="text-input" v-model="newSports.name">
    </div>

    <div class="vertical-checkboxes-selector">
      <div>
        <input type="checkbox" v-model="newSports.indoor" class="checkbox-input">
        Intérieur
      </div>

      <div>
        <input type="checkbox" v-model="newSports.outdoor" class="checkbox-input">
        Extérieur
      </div>
    </div>

    <div class="weather-type-selector select-selector">
      <label for="weather-input">{{ $t('labels.weatherType') }}</label>
      <select name="weather" id="weather-input" v-model="newSports.weatherTypes" multiple>
        <option v-for="weather in weathersType" :value="weather">{{weather}}</option>
      </select>
    </div>

    <div class="buttons">
      <button class="cancel-button" @click="$router.push('/')">{{ $t('labels.cancel') }}</button>
      <button class="register-button" @click.prevent="addSport($router)" role="link">{{ $t('labels.register') }}</button>
    </div>
  </form>
  </Transition>
</template>

<script setup>

import { sportAPIRequest} from "@/components/scripts/SportApiService.js";
import {ref} from "vue";
import {filters} from "@/components/scripts/LicenseesApiService.js";

const weathersType = ref(['SUN', 'RAIN', 'WIND', 'SNOW'])

const newSports = {
  name: null,
  indoor: false,
  outdoor: false,
  weatherTypes: []
}


const addSport = (router) => {
    if(newSports.name !== null){
      sportAPIRequest(newSports)
          .then((response) => {
            filters.statut = 'ACTIF'
            filters.sport = 'ALL'
            filters.weather = []
            filters.indoor = false
            filters.outdoor = false

            console.log(filters)

            router.push('/')
          })
    }
}

</script>

<style scoped>
.v-enter-active,
.v-leave-active {
  transition: 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>