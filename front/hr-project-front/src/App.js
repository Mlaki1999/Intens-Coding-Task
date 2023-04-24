import logo from './logo.svg';
import './App.css';
import SkillList from './skillComponents/SkillList';
import CreateSkill from './skillComponents/CreateSkill';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import CreateCandidate from './candidateComponents/CreateCandidate';
import CandidateList from './candidateComponents/CandidateList';

function App() {
  return (
    <>
      <Router>
        <Routes>       
          <Route path='/addSkill' element={<CreateSkill />} />
        </Routes>
        <Routes>
            <Route path='/skillList' element={<SkillList />} />
        </Routes>
        <Routes>       
          <Route path='/addCandidate' element={<CreateCandidate />} />
        </Routes>
        <Routes>
            <Route path='/candidateList' element={<CandidateList />} />
        </Routes>
        
      </Router>
      <ToastContainer />
      </>
  );
}

export default App;
