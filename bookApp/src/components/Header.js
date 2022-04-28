import React from 'react'
import { Link } from 'react-router-dom'
import '../App.css';
import { Button } from '@mui/material'
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';

function Header() {
  const navigate = useNavigate();
  const logout=()=> {
    sessionStorage.removeItem('username');
    navigate('/');
  }

  return (
    <header>
              <nav className="navbar fixed-top navbar-expand-lg navbar-light bg-dark">
                        <div className="container-fluid">
                          {/* <h2 className="navbar-heading">Book App</h2> */}
                          <h2 className="navbar-heading">Welcome</h2> &nbsp; &nbsp; &nbsp;
                          <h5 className="navbar-heading username">{sessionStorage.getItem('username')}</h5> 
                          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                          </button>
                          <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                              <li className="nav-item">
                                  <Link to="/books" className="nav-link">Home</Link>
                              </li>
                              <li className="nav-item">
                                <Link to="/favourites" className="nav-link active" aria-current="page">My Favourites</Link>
                              </li>
                              <li className="nav-item">
                                <Link to="/recommendation" className="nav-link">All Recommendations</Link>
                              </li>
                              <li className="nav-item">
                                    <Link to="/myRecommendation" className="nav-link">MyRecommendations</Link>
                              </li>
                              <li className="nav-item">
                                {/* <Link to="/"  className="nav-link">Logout</Link> */}
                                <Button variant='text' color='error' startIcon={<LogoutIcon />} onClick={logout}>Logout</Button>
                              </li>
                            </ul>
                          </div>
                        </div>
                    </nav>
      </header>
  )
}

export default Header
