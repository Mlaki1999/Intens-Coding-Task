import React, { useState } from 'react';
import {addCandidate} from '../services/CandidateService'

function CreateCandidate() {
  const [name, setName] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    const newCandidate = { name, dateOfBirth, contactNumber, email };
    addCandidate(newCandidate)
    setName('');
    setDateOfBirth('');
    setContactNumber('');
    setEmail('');
  };

  return (
    <div>
      <h2>Create a new candidate</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(event) => setName(event.target.value)}
          />
        </div>
        <div>
          <label htmlFor="dateOfBirth">Date of birth:</label>
          <input
            type="date"
            id="dateOfBirth"
            value={dateOfBirth}
            onChange={(event) => setDateOfBirth(event.target.value)}
          />
        </div>
        <div>
          <label htmlFor="contactNumber">Contact number:</label>
          <input
            type="text"
            id="contactNumber"
            value={contactNumber}
            onChange={(event) => setContactNumber(event.target.value)}
          />
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
          />
        </div>
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CreateCandidate;
