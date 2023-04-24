import axios from 'axios';
const LOCAL_URL ='http://localhost:8085'
const API_URL = LOCAL_URL + '/api/skills';


export const getAllSkills = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};


export const getSkillById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};


export const addSkill = async (skill) => {
  const response = await axios.post(API_URL, skill);
  return response.data;
};


export const updateSkill = async (id, skill) => {
  const response = await axios.put(`${API_URL}/${id}`, skill);
  return response.data;
};


export const deleteSkill = async (id) => {
  await axios.delete(`${API_URL}/${id}`);
};

export const getSkillsByCandidateId = async (id) => {
  const response = await axios.get(`http://localhost:8085/candidates/allSkills/${id}`);
  return response.data;
};




