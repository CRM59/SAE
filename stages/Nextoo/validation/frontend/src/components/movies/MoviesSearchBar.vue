<template>
  <header>
    <button class="best-movies-per-year-button" @click="$router.push('/best-movies-per-year')"><img src="../../assets/img/stars.png" alt=""></button>
    <button class="add-profile-button" @click="$router.push('/add-profile')"><img src="../../assets/img/add.png" alt=""></button>
    <form action="" @submit.prevent="$emit('MoviesSearchBar_search', pid)">
      <select name="test" id="" v-model="pid">
        <option v-for="profile in profiles" :value="profile.pid">{{profile.name}} #{{(profile.tag)}}</option>
      </select>
    </form>
    <button class="search-button" @click.prevent="$emit('MoviesSearchBar_search', pid)"><img src="../../assets/img/search.png" alt=""></button>
  </header>
</template>


<script setup>
import {onMounted, ref} from "vue";
import {getAllProfiles} from "@/components/scripts/services/ProfileService.js";

  let profiles = ref([]);

  const pid = ref(0);

  onMounted(() => {
    getAllProfiles()
        .then(data => profiles.value = data)
  })


  defineEmits(["MoviesSearchBar_search"])
</script>


<style scoped>
  header{
    display: flex;
    width: 100%;
    justify-content: center;
    margin-top: 0px;
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

  .best-movies-per-year-button{
    background-color: #ffdf5f;
    margin-right: 5px;
  }

  .add-profile-button{
    background-color: #5FFF7F;
  }

  .search-button{
    background-color: #5FBFFF;
  }

  form{
    width: 300px;
    margin: 0 5px 0 5px;
  }

  form select{
    width: 100%;
    height: 40px;
    border-radius: 5px;
    cursor: pointer;
  }
</style>