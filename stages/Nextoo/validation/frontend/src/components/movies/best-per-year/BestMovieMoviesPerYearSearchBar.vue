<template>
  <header>
    <button class="movies-page-button" @click="$router.push('/')"><img src="../../../assets/img/filters.png" alt=""></button>
    <form action="" @submit.prevent="$emit('BestMoviesPerYearSearchBar_search', year)">
      <label for="year-input"></label>
      <input type="number" id="year-input" name="year" v-model="year">
    </form>
    <button class="search-button" @click.prevent="$emit('BestMoviesPerYearSearchBar_search', year)"><img src="../../../assets/img/search.png" alt=""></button>
  </header>
</template>


<script setup>
import {ref} from "vue";


  const year = ref(0);


  defineEmits(["BestMoviesPerYearSearchBar_search"])
</script>


<style scoped>
  header{
    display: flex;
    width: 100%;
    justify-content: center;
    margin-top: 20px;
  }

  button{
    width: 40px;
    height: 40px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button img{
    width: 100%;
  }

  .movies-page-button{
    background-color: #ffaf5f;
  }

  .search-button{
    margin-left: 7px;
    background-color: #5FBFFF;
  }

  form{
    width: 300px;
    margin: 0 5px 0 5px;
  }

  form input{
    width: 100%;
    height: 36px;
    border-radius: 5px;
    cursor: pointer;
    border: solid 1px grey;
  }
</style>