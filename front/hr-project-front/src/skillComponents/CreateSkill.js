import React, { useState } from 'react';
import axios from 'axios';
import {addSkill} from '../services/SkillService'

const CreateSkill = () => {
  const [name, setName] = useState('');

  const handleNameChange = (e) => {
    setName(e.target.value);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const newSkill ={
      name: name
    }
    addSkill(newSkill)
      .then(response => {
        console.log(response.data);
        setName('');
      })
      .catch(error => {
        console.log(error);
      });
  }

  return (
    <div>
      <h2>Create a New Skill</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input type="text" value={name} onChange={handleNameChange} />
        </div>
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CreateSkill;