<template>
  <div class="title"><h1>{{$t('profile.addProfile')}}</h1></div>
  <form action="">
    <div class="text-input">
      <label for="profil-name-input">{{$t('profile.profileName')}}*</label>
      <input type="text" name="" id="profil-name-input" v-model="profile.name">
    </div>

    <div class="text-input">
      <label for="profil-tag-input">Tag* (4 {{ $t('profile.characters') }} )</label>
      <input type="text" name="" id="profil-name-input" v-model="profile.tag">
    </div>

    <div class="text-input">
      <label for="language-input">{{$t('movies.language')}} ({{$t('profile.optional')}})</label>
      <select name="" class="unique-select-input" v-model="profile.lang">
        <option v-for="language in languages" :key="language.iso_639_1" :value="language.iso_639_1">{{language.name !== '' ? language.name : language.english_name}}</option>
      </select>
    </div>

    <div>
      <label for="adult-input">{{$t('movies.forAdult')}}</label>
      <input type="checkbox" name="" id="adult-input" v-model="profile.adult">
    </div>

    <div class="text-input">
      <label for="genres-input">{{$t('movies.genres')}} ({{$t('profile.optional')}})</label>
      <select name="genres" class="multiple-select-input" v-model="profile.genres" multiple>
        <option v-for="genre in genres" :key="genre.id" :value="genre.id">{{genre.name}}</option>
      </select>
    </div>

    <button class="create-button" @click.prevent="addProfile($router)">{{$t('profile.create')}}</button>
    <button class="cancel-button" @click.prevent="$router.push('/')">{{$t('profile.cancel')}}</button>
  </form>
</template>

<script setup>
import {genres, languages} from "@/components/scripts/services/MoviesService.js";
import {addProfilApiRequest, getAllProfiles} from "@/components/scripts/services/ProfileService.js";
import {onMounted, ref} from "vue";

    const profile = {
      name: "",
      tag: "",
      lang: "",
      adult: false,
      genres: []
    }


    const tags = ref([])

onMounted(() => {
  getAllProfiles()
      .then(data => {
        tags.value = data.map((d) => d.tag);
      })
})


    const addProfile = (router) => {

      if(
          profile.name !== null
          && profile.tag !== null
          && profile.name !== ''
          && profile.tag !== ''
          && profile.tag.length === 4
          && !tags.value.includes(profile.tag)
      ){
        addProfilApiRequest(profile)
            .then(() => {
              router.push('/')
            })
      }
    }
</script>

<style scoped>

.title{
  display: flex;
  justify-content: center;
}

form{
  display: flex;
  flex-direction: column;
  align-items: center;
}

form > *{
  margin: 15px;
}

label {
  margin-bottom: 5px;
}

form .text-input{
  display: flex;
  flex-direction: column;
}

form .text-input input {
  width: 250px;
  height: 30px;
  font-size: 18px;
  border-radius: 5px;
}


form .unique-select-input {
  width: 250px;
  height: 50px;
  font-size: 18px;
  border-radius: 5px;
}


form .multiple-select-input {
  width: 250px;
  height: 150px;
  font-size: 18px;
  border-radius: 5px;
}


button{
  width: 250px;
  height: 40px;
  border: none;
  font-size: 25px;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.create-button{
  background-color: #5FBFFF;
}

.cancel-button{
  background-color: #ff5f5f;
}
</style>