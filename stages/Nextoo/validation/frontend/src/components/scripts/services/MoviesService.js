import axios from "axios";
import {ref} from "vue";

export const genres = ref([]);
export const languages = ref([]);


export async function searchBestMoviesApiRequest(pid) {
    return (await axios.get(`http://localhost:8080/movies/${pid}`)).data
}


export async function searchBestMoviesPerYearApiRequest(year) {
    return (await axios.get(`http://localhost:8080/movies/best-movies-per-year/${year}`)).data
}



export async function addWatchedMovieApiRequest(movieWatched) {
    await axios.post(`http://localhost:8080/movies/watched`, movieWatched)
}


export async function getAllGenres(){
    return (await axios.get('https://api.themoviedb.org/3/genre/movie/list?language=fr&api_key=7880e9f40b695f740752766513cef33f')).data
}

export async function getAllLanguages(){
    return (await axios.get('https://api.themoviedb.org/3/configuration/languages?api_key=7880e9f40b695f740752766513cef33f')).data
}