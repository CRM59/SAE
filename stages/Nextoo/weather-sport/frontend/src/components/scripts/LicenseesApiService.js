import axios from "axios";

export const filters = {
    statut: 'ACTIF',
    sport: 'ALL',
    weather: [],
    indoor: false,
    outdoor: false
}

export function licenseesAPIRequest(filters){
    return axios.get(licenseesApiService(filters))
}


export async function licenseesAPIPostRequest(newLicensees){
    return (await axios.post('http://localhost:8080/licensees/add-licensees', newLicensees));
}

export async function licenseesAPIPutRequest(updatedLicensees){
    return (await axios.put('http://localhost:8080/licensees/update-licensees', updatedLicensees));
}



function licenseesApiService(){

    let link = "http://localhost:8080/licensees/criteria?";

    if(filters.statut === "ACTIF"){
        link += 'active=' + true;
    }
    else if(filters.statut === 'INACTIF'){
        link += 'active=' + false;
    }

    if(filters.sport !== null && filters.sport !== 'ALL'){
        link += "&sportName=" + filters.sport
    }

    if(filters.weather !== null){
        filters.weather.forEach((w) => link += "&weathersTypes=" + w)
    }

    link += "&isIndoor=" + filters.indoor

    link += "&isOutdoor=" + filters.outdoor

    return link
}