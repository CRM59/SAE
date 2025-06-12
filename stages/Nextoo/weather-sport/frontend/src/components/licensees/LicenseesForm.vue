<template>
  <Transition appear>
    <form action="" class="licensees-form add-form">
      <h1 class="title"> {{isUpdating ? $t('labels.modifyLicensees') : $t('labels.createLicensees')}}</h1>

      <div class="person-name-selector">
        <div class="lastName-selector text-selector">
          <label class="lastName-label" for="lastName-input">{{ $t('labels.lastName') }}</label>
          <input :disabled="isUpdating" type="text" name="lastName" id="lastName-input" class="text-input" v-model="newLicensees.personLastName">
        </div>

        <div class="firstName-selector text-selector">
          <label class="firstName-label" for="firstName-input">{{ $t('labels.firstName') }}</label>
          <input :disabled="isUpdating" type="text" name="firstName" id="firstName-input" class="text-input" v-model="newLicensees.personFirstName">
        </div>
      </div>

      <div class="sport-name-selector select-selector">
        <label for="sport-name-input">{{ $t('labels.sport') }}</label>
        <select :disabled="isUpdating" name="sportName" id="sport-name-input" class="select-input" v-model="newLicensees.sportName">
          <option v-for="sport in sports" :value="sport.name">{{sport.name}}</option>
        </select>
      </div>

      <div class="dates-selector">
        <div class="start-date-selector text-selector">
          <label class="start-date-label" for="start-date-input">{{ $t('labels.startValidity') }}</label>
          <input type="date" name="startDate" id="start-date-input" class="text-input" v-model="newLicensees.startDate">
        </div>

        <div class="end-date-selector text-selector">
          <label class="end-date-label" for="end-date-input">{{ $t('labels.endValidity') }}</label>
          <input type="date" name="endDate" id="end-date-input" class="text-input" v-model="newLicensees.endDate">
        </div>
      </div>


      <div class="buttons">
        <button class="cancel-button" @click="$router.push('/')">{{ $t('labels.cancel') }}</button>
        <button class="register-button" @click.prevent="isUpdating ? updateLicensees($router) : addLicensees($router)" role="link">{{ isUpdating ? $t('labels.modify') : $t('labels.create') }}</button>
      </div>
    </form>
  </Transition>
</template>

<script setup>
import {licenseesAPIPostRequest, licenseesAPIPutRequest} from "@/components/scripts/LicenseesApiService.js";
import {onMounted, ref} from "vue";
import {updateAllSportsAPIRequest} from "@/components/scripts/SportApiService.js";
import {sports} from "@/components/scripts/SportApiService.js";
const route = useRoute()

import {useRoute} from "vue-router";

const newLicensees = {
  personFirstName: route.query.firstName,
  personLastName: route.query.lastName,
  sportName: route.query.sportName,
  startDate: String,
  endDate: String
}

const isUpdating = ref(false)

onMounted(() => {
  if(route.query.lastName !== undefined){
    isUpdating.value = true
  }
})


onMounted(() => {
  if(sports.value.length === 0){
    updateAllSportsAPIRequest()
  }
})


const addLicensees = (router) => {
  if(newLicensees.personLastName !== null
      && newLicensees.personFirstName !== null
      && newLicensees.sportName !== null
      && newLicensees.startDate !== null
      && newLicensees.endDate !== null){

    licenseesAPIPostRequest(newLicensees)
        .then((response) => {
          router.push('/')
        })
  }
}


const updateLicensees = (router) => {
  if(newLicensees.startDate !== null && newLicensees.endDate !== null){
    licenseesAPIPutRequest(newLicensees)
        .then((response) => {
          router.push('/')
        })
  }
}

</script>


<style scoped>
.person-name-selector{
  display: flex;
  justify-content: space-around;
  width: 600px;
}

.dates-selector{
  display: flex;
  justify-content: space-around;
  width: 600px;
}

.sport-name-selector{
  width: 200px;
}


.v-enter-active,
.v-leave-active {
  transition: 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>