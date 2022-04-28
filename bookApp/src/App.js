import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Login from './components/Login';
import BookList from './components/BookList';
import RegisterValidation from './components/RegisterValidation';
import Recommendation from './components/Recommendation';
import Favourite from './components/Favourites';
import MyRecommendation from './components/MyRecommendation';
import Home from './Home';
import Demo from './Demo';

function App() {
  return (
    <div className="App">
      <Router>
          <Routes>
            <Route exact path="/" element={<Login/>}/>
            {/* <Route exact path="/" element={<Demo/>}/> */}
            <Route exact path="/register" element={<RegisterValidation/>}/>
            <Route exact path="/books" element={<BookList/>}/>
            <Route exact path="/favourites" element={<Favourite/>}/>
            <Route exact path="/recommendation" element={<Recommendation/>}/>
            <Route exact path="/myRecommendation" element={<MyRecommendation/>}/>
            {/* <Route exact path="/home" element={<Home/>}/> */}
          </Routes>
      </Router>
    </div>
  );
}

export default App;
