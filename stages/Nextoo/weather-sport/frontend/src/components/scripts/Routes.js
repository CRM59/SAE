import SportForm from "@/components/sport/SportForm.vue";
import LicenseesForm from "@/components/licensees/LicenseesForm.vue";
import Research from "@/components/search/Research.vue";

export const routes = [
    {path: '/', component: Research},
    {path: '/SportsForm', component: SportForm},
    {path: '/LicenseesForm', component: LicenseesForm, props: true}
]