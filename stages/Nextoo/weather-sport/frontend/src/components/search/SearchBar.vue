<template>
  <Transition appear>
    <form class="search-bar">
      <div class="selectors">

        <div class="selectors-top">
          <div class="statut-selector text-selector">
            <label for="statut-input">{{ $t('labels.licenseesStatus') }}</label>
            <select name="statut" id="statut-input" class="select-input" v-model="filters.statut">
              <option value="ACTIF">ACTIF</option>
              <option value="INACTIF">INACTIF</option>
            </select>
          </div>

          <div class="sport-selector text-selector">
            <label for="sport-input">{{ $t('labels.sport') }}</label>
            <select name="sport" id="sport-input" class="select-input" v-model="filters.sport">
              <option value="ALL">ALL</option>
              <option v-for="sport in sports" :value="sport.name">{{sport.name}}</option>
            </select>
          </div>
        </div>

        <div class="select-selector">
          <label for="weather-input">{{ $t('labels.weatherType') }}</label>
          <select name="weather" id="weather-input" class="multi-select-input" multiple v-model="filters.weather">
            <option v-for="weather in weathersType" :value="weather">{{weather}}</option>
          </select>
        </div>

      </div>
      <div class="checkboxes">
        <div>
          <input type="checkbox" class="checkbox-input" v-model="filters.indoor">
          Intérieur
        </div>

        <div>
          <input type="checkbox" class="checkbox-input" v-model="filters.outdoor">
          Extérieur
        </div>

      </div>
      <div class="valid">

        <button class="search-button" @click.prevent="$emit('SearchBar_searchByFilters')">{{ $t('labels.research') }}</button>
      </div>
    </form>
  </Transition>
</template>

<script setup>

import {ref} from "vue";
import {filters} from "@/components/scripts/LicenseesApiService.js";
import {sports} from "@/components/scripts/SportApiService.js";

const weathersType = ref(['SUN', 'RAIN', 'WIND', 'SNOW']);


defineEmits(['SearchBar_searchByFilters'])


</script>

<style scoped>
.v-enter-active,
.v-leave-active {
  transition: 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
  margin-top: 100px;
}
</style>

