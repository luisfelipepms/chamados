import apiRequest from "./client";

export const getAtendimentos = (filters ={}) => apiRequest("/atendimento", {method: "GET", params: filters});
export const finalizarAtendimento = (id) => apiRequest(`/atendimento/finish/${id}`, {method: "PATCH"});