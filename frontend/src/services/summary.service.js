import httpClient from "../http-common";

const getSummary1 = (month, year) => {
    return httpClient.get(`/api/summary1/${month}/${year}`);
}

const getSummary2 = (month, year) => {
    return httpClient.get(`/api/summary2/${month}/${year}`);
}


export default { getSummary1, getSummary2 };