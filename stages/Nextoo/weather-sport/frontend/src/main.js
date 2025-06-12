import { createApp } from 'vue'
import App from './App.vue'
import "./assets/css/forms.css"
import "./assets/css/main.css"
import "./assets/css/header.css"
import "./assets/css/searchBar.css"
import "./assets/css/table.css"
import {createRouter, createWebHistory} from "vue-router";
import {routes} from "@/components/scripts/Routes.js";
import {createI18n} from "vue-i18n";
import labelsFR from './assets/local/labels-fr.json'
import labelsEN from './assets/local/labels-en.json'


const i18n = createI18n({
    locale: 'fr',
    messages: {
        fr: {
            labels: labelsFR
        },
        en: {
            labels: labelsEN
        }
    }
})

const router = createRouter({
    history: createWebHistory(),
    routes
})



const app = createApp(App)
app.use(router)
app.use(i18n)
app.mount('#app')
