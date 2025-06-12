<template>
  <MoviesSearchBar @MoviesSearchBar_search="searchBestMovies"/>
  <MoviesTable :pid="pid" :movies="movies" @MoviesTable_markMovieAsWatched="addMoviesWatched"/>
</template>

<script setup>
import MoviesSearchBar from "@/components/movies/MoviesSearchBar.vue";
import MoviesTable from "@/components/movies/MoviesTable.vue";
import {ref} from "vue";
import {
  addWatchedMovieApiRequest,
  searchBestMoviesApiRequest
} from "@/components/scripts/services/MoviesService.js";

  const pid = ref(0)
  const movies = ref([])

  const searchBestMovies = (profileId) => {
    searchBestMoviesApiRequest(profileId)
        .then(data => {
          pid.value = profileId
          movies.value = data
        })
  }



  const addMoviesWatched = (movieWatched) => {
    addWatchedMovieApiRequest(movieWatched)
        .then(() => searchBestMovies(movieWatched.pid))
  }
</script>

<style scoped>
</style>