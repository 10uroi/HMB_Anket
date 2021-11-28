import './App.css';
import MainPage from './pages/MainPage';
import AdminPage from './pages/AdminPage';
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="admin" element={<AdminPage />} />
      </Routes>
    </div>
  );
}

export default App;
