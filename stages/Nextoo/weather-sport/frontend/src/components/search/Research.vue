<template>
  <div class="add-buttons-list">
    <button class="add-button" @click="$router.push('/SportsForm')" role="link">+ {{ $t('labels.sport') }}</button>
    <button class="add-button" @click="$router.push('/LicenseesForm')" role="link">+ {{ $t('labels.licensees') }}</button>
  </div>

  <SearchBar @SearchBar_searchByFilters="searchByFilters" />
  <Table :licensees="licensees" :statut="statut" />
</template>

<script setup>

import SearchBar from "@/components/search/SearchBar.vue";
import Table from "@/components/search/Table.vue";
import {onMounted, ref} from "vue";
import {updateAllSportsAPIRequest} from "@/components/scripts/SportApiService.js";
import {filters, licenseesAPIRequest} from "@/components/scripts/LicenseesApiService.js";


const licensees = ref([]);
const statut = ref('ACTIF');

defineExpose({
  licensees,
  statut
})

onMounted(() => {
  updateAllSportsAPIRequest()
  licenseesAPIRequest()
      .then((response) => {
        return response.data
      })
      .then((data) => {
        licensees.value = data
        statut.value = filters.statut
      })
})


const searchByFilters = () => {
  licenseesAPIRequest()
      .then((response) => {
        return response.data
      })
      .then((data) => {
        licensees.value = data
        statut.value = filters.statut
      })
}

</script>

<style scoped>

</style>