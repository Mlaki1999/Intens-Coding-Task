import React, { useState, useEffect } from "react";
import {getAllCandidates,deleteCandidate,searchCandidatesBySkill, removeSkillFromCandidate} from '../services/CandidateService'
import {getSkillsByCandidateId} from '../services/SkillService'
import UpdateCandidate from "./UpdateCandidate";

const CandidateList = () => {
  const [candidates, setCandidates] = useState([]);
  const [editingCandidate, setEditingCandidate] = useState(null);
  const [updateId, setUpdateId] = useState(null);
  const [candidateSkills, setCandidateSkills] = useState([]);
  const [candidatesWithSkill, setCandidatesWithSkills] = useState([]);
  const [nameSkill, setNameSkill] = useState('');
  const [idToDeleteSkill, setIdToDeleteSkill] = useState(null);
  useEffect(() => {
    getAllCandidates()
    .then(data => setCandidates(data))
    .catch(console.error)
  }, []);

  const getSkills = (id) => {
    getSkillsByCandidateId(id).then(data => setCandidateSkills(data)).catch(console.error)
  }

  const getCandidatesWithSkill = (name) => {
    searchCandidatesBySkill(name).then(data => setCandidatesWithSkills(data)).catch(console.error)
  }

  const handleEditCandidate = (candidate) => {
    setEditingCandidate(candidate);
    setUpdateId(candidate.id)
  };

  const handleDelete = async (id) => {
    deleteCandidate(id)
    .then(()=>{
      const newCandidates = candidates.filter(can => can.id !== id);
      setCandidates(newCandidates);
    })
    .catch(error => console.log(error));
  };

  return (
    <div>

Search
      <input
            type="text"
            id="nameSkill"
            value={nameSkill}
            onChange={(event) => setNameSkill(event.target.value)}
          />
      <button onClick={()=>{getCandidatesWithSkill(nameSkill)}}>Search by skill</button>
    <div>

    People with skills:
    <table>
  <thead>
    <tr>
      <th>Name</th>
      <th>email</th>
    </tr>
  </thead>
  <tbody>
    {candidatesWithSkill.map((can) => (
      <tr key={can.id}>
        <td>{can.name}</td>
        <td>{can.email}</td>
      </tr>
    ))}
  </tbody>
</table>
    </div>
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
                <button onClick={() =>handleEditCandidate(candidate)}>Edit</button>
                <button onClick={() => handleDelete(candidate.id)}>Delete</button>
                <button onClick={() => {getSkills(candidate.id); setIdToDeleteSkill(candidate.id)}}>Candidate Skills</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    <div>
      {editingCandidate && (
        <UpdateCandidate candidateId = {updateId} setCandidates={setCandidates} setEditingCandidate={setEditingCandidate} candidates={candidates}/>
      )}
    </div>
    <p>----------------</p>
<p>----------------</p>
<p>----------------</p>
<p>----------------</p>
<p>----------------</p>
<p>----------------</p>
<p>----------------</p>
    <div>
     
    <table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Skill Name</th>
      <th>Delete skill from candidate</th>
    </tr>
  </thead>
  <tbody>
    {candidateSkills.map((skill) => (
      <tr key={skill.id}>
        <td>{skill.id}</td>
        <td>{skill.name}</td>
        <td><button onClick={()=>{
          removeSkillFromCandidate(idToDeleteSkill, skill.id);
        }}>deleteSkill</button></td>
      </tr>
    ))}
  </tbody>
</table>
    </div>
    </div>
  );
};

export default CandidateList;
