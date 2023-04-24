import { useState, useEffect } from 'react';
import {getCandidateById, updateCandidate} from '../services/CandidateService'

const UpdateCandidate = ({ candidateId, candidates, setCandidates, setEditingCandidate }) => {
  const [candidate, setCandidate] = useState({
    name: '',
    contactNumber: '',
    email: '',
  });

  useEffect(() => {
    getCandidateById(candidateId)
      .then((response) => {
        setCandidate(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [candidateId]);

  const handleSubmit = (e) => {
    e.preventDefault();
    updateCandidate(candidateId, candidate)
      .then((response) => {
        console.log(response.data);
        setCandidates(candidates.map(c => c.id === response.id ? response : c))
        setEditingCandidate(null);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="name">Name:</label>
      <input
        type="text"
        id="name"
        value={candidate.name}
        onChange={(e) =>
          setCandidate({ ...candidate, name: e.target.value })
        }
      />

      <label htmlFor="contactNumber">Contact Number:</label>
      <input
        type="text"
        id="contactNumber"
        value={candidate.contactNumber}
        onChange={(e) =>
          setCandidate({ ...candidate, contactNumber: e.target.value })
        }
      />

      <label htmlFor="email">Email:</label>
      <input
        type="text"
        id="email"
        value={candidate.email}
        onChange={(e) =>
          setCandidate({ ...candidate, email: e.target.value })
        }
      />

      <button type="submit">Update Candidate</button>
    </form>
  );
};

export default UpdateCandidate;