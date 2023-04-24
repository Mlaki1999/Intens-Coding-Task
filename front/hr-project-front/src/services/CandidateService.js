import axios from 'axios';
const LOCAL_URL ='http://localhost:8085'
const BASE_URL = LOCAL_URL + '/candidates';


const getAllCandidates = async () => {
  const response = await axios.get(BASE_URL);
  return response.data;
};


const getCandidateById = async (id) => {
  const response = await axios.get(`${BASE_URL}/${id}`);
  return response.data;
};


const addCandidate = async (candidate) => {
  const response = await axios.post(BASE_URL, candidate);
  return response.data;
};

const updateCandidate = async (id, candidate) => {
  const response = await axios.put(`${BASE_URL}/${id}`, candidate);
  return response.data;
};


const deleteCandidate = async (id) => {
  await axios.delete(`${BASE_URL}/${id}`);
};


const searchCandidatesBySkill = async (skill) => {
  const response = await axios.get(`${BASE_URL}/search?skill=${skill}`);
  return response.data;
};

const searchCandidatesBySkill2 = async (skill) => {
  const response = await axios.get(`${BASE_URL}/search2?skill=${skill}`);
  return response.data;
};

const searchCandidatesByName = async (name) => {
  const response = await axios.get(`${BASE_URL}/searchByName?name=${name}`);
  return response.data;
};


const addSkillToCandidate = async (id, skillId) => {
  const response = await axios.post(`${BASE_URL}/${id}/skills?skillId=${skillId}`);
  return response.data;
};


const removeSkillFromCandidate = async (id, skillId) => {
  const response = await axios.delete(`${BASE_URL}/${id}/skills/${skillId}`);
  return response.data;
};

export {
  getAllCandidates,
  getCandidateById,
  addCandidate,
  updateCandidate,
  deleteCandidate,
  searchCandidatesBySkill,
  searchCandidatesBySkill2,
  searchCandidatesByName,
  addSkillToCandidate,
  removeSkillFromCandidate,
};
