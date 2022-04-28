import React, { useState } from 'react'
import { Button, TextField } from '@mui/material'
import './css/Login.css';
import PersonIcon from '@mui/icons-material/Person';
import LoginLogo from '../images/bg1.jpg';
import KeyIcon from '@mui/icons-material/Key';
import RestService from '../services/RestService';
import LoginIcon from '@mui/icons-material/Login';
import { Link } from 'react-router-dom';
import GroupIcon from '@mui/icons-material/Group';
import { useNavigate } from 'react-router-dom';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import OutlinedInput from '@mui/material/OutlinedInput';
import IconButton from '@mui/material/IconButton';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

function Login() {
  const navigate = useNavigate();
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [values, setValues] = useState({
    showPassword: false
  })
  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };



  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };

  const loginUser = (e) => {
    e.preventDefault();
    let user = {
      userName: userName, password: password
    }
    console.log(JSON.stringify(user));
    RestService.loginUser(user)
      .then((res) => {
        setErrorMsg('');
        if (res.status === 200) {
          sessionStorage.setItem('token', res.data);
          sessionStorage.setItem('username', userName);
          navigate('/books');
        }
      })
      .catch((err) => {
        setErrorMsg('Invalid Username/Password');
      })
  }
  return (

    <section className="login py-5 bg-light">
      <div className="container">

        <div className="row g-0 login-row">

          <div className="col-lg-5 loginImage">
            <img src={LoginLogo} className="img-fluid" alt="login" />
            <h1 className="centered">Book App</h1>
          </div>
          <div className="col-lg-7 text-center py-5">
            <h1>Welcome Back</h1>
            <div className="icon d-flex align-items-center justify-content-center">
              <GroupIcon style={{ fontSize: 100, color: '#6867AC' }} />
            </div>
            <form>
              <div className="form-row pt-2">
                <div className="offset-1 col-lg-10">
                  <PersonIcon fontSize="large" />
                  <TextField label='User Name' size='small' value={userName}
                    onChange={(e) => setUserName(e.target.value)} required />
                </div>
              </div>

              <div className="form-row pt-3">
                <div className="offset-1 col-lg-10">
                  <KeyIcon fontSize="large" />
                  {/* <TextField label='Password' size='small' value={password}
                        onChange={(e) => setPassword(e.target.value)} required /> */}
                  <FormControl sx={{ m: 0, width: '25ch' }} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                    <OutlinedInput
                      id="outlined-adornment-password"
                      type={values.showPassword ? 'text' : 'password'}
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      endAdornment={
                        <InputAdornment position="end">
                          <IconButton
                            aria-label="toggle password visibility"
                            onClick={handleClickShowPassword}
                            onMouseDown={handleMouseDownPassword}
                            edge="end"
                          >
                            {values.showPassword ? <VisibilityOff /> : <Visibility />}
                          </IconButton>
                        </InputAdornment>
                      }
                      label="Password" required size='medium'
                    />
                  </FormControl>
                </div>
              </div>


              <div className="form-row pt-3">
                <div className="offset-1 col-lg-10">
                  <Button variant='contained' color='success' startIcon={<LoginIcon />} onClick={loginUser}>Log In</Button>
                </div>
              </div>

              <div className="form-row pt-3">
                <p style={{ color: 'red' }}>{errorMsg}</p>
              </div>

              <div className="d-flex align-items-center justify-content-center pb-4 pt-3">
                <p className="mb-0 me-2">Don't have an account?</p>
                <Link to={"/register"} className="btn btn-outline-danger">Create new</Link>
              </div>

            </form>
          </div>
        </div>
      </div>

    </section>


  )
}

export default Login
