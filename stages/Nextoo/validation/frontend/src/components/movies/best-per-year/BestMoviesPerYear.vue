<template>
  <BestMovieMoviesPerYearSearchBar @BestMoviesPerYearSearchBar_search="searchBestMoviesPerYear"/>
  <BestMoviesPerYearTable :movies="movies"/>
</template>

<script setup>
import {ref} from "vue";
import {
  searchBestMoviesPerYearApiRequest
} from "@/components/scripts/services/MoviesService.js";
import BestMovieMoviesPerYearSearchBar from "@/components/movies/best-per-year/BestMovieMoviesPerYearSearchBar.vue";
import BestMoviesPerYearTable from "@/components/movies/best-per-year/BestMoviesPerYearTable.vue";

  const movies = ref([])

  const searchBestMoviesPerYear = (year) => {
    searchBestMoviesPerYearApiRequest(year)
        .then(data => {
          movies.value = data.moviesList
        })
  }
</script>

<style scoped>
</style>