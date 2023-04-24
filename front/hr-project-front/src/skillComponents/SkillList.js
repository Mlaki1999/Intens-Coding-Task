import { useState, useEffect } from 'react';
import axios from 'axios';
import {getAllSkills, getSkillById, addSkill, updateSkill, deleteSkill} from '../services/SkillService';
import {getAllCandidates,deleteCandidate,searchCandidatesBySkill, addSkillToCandidate} from '../services/CandidateService'

export default function SkillList() {
  const [candidates, setCandidates] = useState([]);
  const [skills, setSkills] = useState([]);
  const [editingSkill, setEditingSkill] = useState(null);
  const [skillToAdd, setSkillToAdd] = useState(null);


  useEffect(() => {
    getAllCandidates()
    .then(data => setCandidates(data))
    .catch(console.error)
  }, []);

  useEffect(()=>{
    getAllSkills().then(data => setSkills(data))
    .catch(console.error);
  }, [])

  const handleEditSkill = (skill) => {
    setEditingSkill(skill);
  };

  const handleCancelEdit = () => {
    setEditingSkill(null);
  };

  const handleUpdateSkill = (updatedSkill) => {
   updateSkill(updatedSkill.id, updatedSkill)
      .then(response => {
        setSkills(skills.map(s => s.id === response.id ? response : s))
        setEditingSkill(null);
      })
      .catch(error => console.log(error));
  };

  const handleDeleteSkill = (id) => {
    deleteSkill(id)
      .then(() => {
        const newSkills = skills.filter(skill => skill.id !== id);
        setSkills(newSkills);
      })
      .catch(error => console.log(error));
  };

  return (
    <div>
      <h2>List of Skills</h2>
      <ul>
        {skills.map(skill => (
          <li key={skill.id}>
            {editingSkill && editingSkill.id === skill.id ? (
              <SkillForm
                skill={editingSkill}
                onSubmit={handleUpdateSkill}
                onCancel={handleCancelEdit}
              />
            ) : (
              <div>
                <div>{skill.name}</div>
                <button onClick={() => handleEditSkill(skill)}>Edit</button>
                <button onClick={() => handleDeleteSkill(skill.id)}>Delete</button>
                <button onClick={() => setSkillToAdd(skill)}> chooseSkillToAdd </button>
              </div>
            )}
          </li>
        ))}
      </ul>

      <h3>Chosed skill to add to candidate: {skillToAdd?.name ?? ''}</h3>
      <div>
      <div>
      <h1>Candidates</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Contact Number</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {candidates.map((candidate) => (
            <tr key={candidate.id}>
              <td>{candidate.id}</td>
              <td>{candidate.name}</td>
              <td>{candidate.dateOfBirth}</td>
              <td>{candidate.contactNumber}</td>
              <td>{candidate.email}</td>
              <td>
                <button onClick={() => {
                  addSkillToCandidate(candidate.id, skillToAdd.id)
                }}>Add chosed skill</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
      </div>
    </div>
  );
}

function SkillForm({ skill = { name: '' }, onSubmit, onCancel }) {
  const [name, setName] = useState(skill.name);

  const handleSubmit = (event) => {
    event.preventDefault();
    const updatedSkill = { ...skill, name };
    onSubmit(updatedSkill);
  };

  const handleCancel = () => {
    onCancel();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" value={name} onChange={(event) => setName(event.target.value)} />
      <button type="submit">Save</button>
      <button type="button" onClick={handleCancel}>Cancel</button>
    </form>
  );
}