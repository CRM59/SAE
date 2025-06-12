<template>
  <div class="language-buttons">
    <button class="french-button" @click.prevent="$i18n.locale='fr'">
      <img src="./assets/img/french.png" alt="french">
    </button>
    <button class="english-button" @click.prevent="$i18n.locale='en'">
      <img src="./assets/img/english.png" alt="english">
    </button>
  </div>
  <RouterView></RouterView>
</template>

<script setup>
import {onMounted} from "vue";
import {genres, getAllGenres, getAllLanguages, languages} from "@/components/scripts/services/MoviesService.js";



onMounted(() => {
  getAllGenres().then(data => {
    genres.value = data.genres;
  })
})


onMounted(() => {
  getAllLanguages().then(data => {
    languages.value = data.sort((a, b) =>
        (a.name !== '' ? a.name : a.english_name) < (b.name !== '' ? b.name : b.english_name)
            ? -1 : 1
    );
  })
})
</script>

<style scoped>
.language-buttons {
  display: flex;
  justify-content: center;
  margin-top: 100px;
}

.language-buttons button {
  width: 65px;
  height: 65px;
  margin: 0 15px 0 15px;
  background: none;
  border: none;
  cursor: pointer;
}

.language-buttons button img {
  width: 100%;
}

.french-button:hover {
  filter: drop-shadow(0px 0px 5px blue);
}

.english-button:hover {
  filter: drop-shadow(0px 0px 5px red);
}
</style>