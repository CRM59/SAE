import Movies from "@/components/movies/Movies.vue";
import ProfileForm from "@/components/profils/ProfileForm.vue";
import BestMoviesPerYear from "@/components/movies/best-per-year/BestMoviesPerYear.vue";

export const routes = [
    {path: '/', component: Movies},
    {path: '/add-profile', component: ProfileForm},
    {path: '/best-movies-per-year', component: BestMoviesPerYear}
]