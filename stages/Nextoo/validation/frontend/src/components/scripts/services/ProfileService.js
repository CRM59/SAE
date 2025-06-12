import axios from "axios";

export async function addProfilApiRequest(profil) {
    return (await axios.post(`http://localhost:8080/profiles/add`, profil))
}


export async function getAllProfiles(){
    return (await axios.get('http://localhost:8080/profiles')).data
}