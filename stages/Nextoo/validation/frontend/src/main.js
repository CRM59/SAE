import "./assets/css/main.css"

import { createApp } from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from "vue-router";
import {routes} from "@/components/scripts/Routes.js";
import {createI18n} from "vue-i18n";
import fr from './assets/local/fr.json'
import en from './assets/local/en.json'

const router = createRouter({
    history: createWebHistory(),
    routes
})


const i18n = createI18n({
    locale: 'fr',
    messages: {
        "fr": fr,
        "en": en
    }
})

const app = createApp(App)
app.use(router)
app.use(i18n)
app.mount('#app')
