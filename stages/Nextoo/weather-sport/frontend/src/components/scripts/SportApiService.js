import axios from "axios";
import {ref} from "vue";

export const sports = ref([])

export async function sportAPIRequest(newSport){
    return (await axios.post('http://localhost:8080/sports/add-sport', newSport)).data;
}


export async function updateAllSportsAPIRequest(){
    sports.value = (await axios.get('http://localhost:8080/sports')).data;
}