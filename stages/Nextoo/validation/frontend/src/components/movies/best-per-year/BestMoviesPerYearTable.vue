<template>
  <table>
    <thead>
    <tr>
      <th>{{$t('movies.title')}}</th>
      <th>{{$t('movies.description')}}</th>
      <th>{{$t('movies.genres')}}</th>
      <th>{{$t('movies.language')}}</th>
      <th>{{$t('movies.forAdult')}}</th>
      <th>{{$t('movies.releaseDate')}}</th>
      <th>{{$t('movies.rating')}}</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="movie in movies" :key="movie.id">
      <td>{{movie.title}}</td>
      <td>{{movie.overview}}</td>
      <td>{{generateGenres(movie.genres)}}</td>
      <td>{{ movie.language }}</td>
      <td>
        <img v-if="movie.forAdult" src="../../../assets/img/check.png" class="statut-icon" alt="for adult">
        <img v-if="!movie.forAdult" src="../../../assets/img/cross.png" class="statut-icon" alt="not for adult">
      </td>
      <td>{{ movie.releaseDate }}</td>
      <td>{{ movie.rating }}/10</td>
    </tr>
    </tbody>
  </table>
</template>


<script setup>

  import {genres} from "@/components/scripts/services/MoviesService.js";

  defineProps({
    pid: Number,
    movies: Array
  })


  const generateGenres = (genresIds) => {
    return genres.value
        .filter(genre =>
                genresIds
                    .includes(genre.id))
        .map(genre => genre.name)
        .join(', ')
  }

</script>


<style scoped>
table{
  border-collapse: collapse;
  text-align: center;
  margin: 100px auto 0 auto;
  width: 60%;
}

td, th {
  border: 1px solid #dddddd;
  padding: 8px;
  text-align: center;
}

.watched-button-td{
  border: none;
}

.statut-icon{
  width: 25px;
  height: 25px;
}


.watched-button{
  height: 40px;
  width: 40px;
  background: none;
  border: none;
  cursor: pointer;
}

.watched-button img{
  width: 100%;
}
</style>