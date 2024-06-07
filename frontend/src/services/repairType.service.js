import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/repairTypes/');
}

const create = data => {
    return httpClient.post("/api/repairTypes/", data);
}

const remove = id => {
    return httpClient.delete(`/api/repairTypes/${id}`);
}

export default { getAll, create, remove };