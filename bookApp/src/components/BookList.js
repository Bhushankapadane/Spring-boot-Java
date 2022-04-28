import React, { useState, useEffect } from 'react'
import './css/BookList.css';
import Card from './Card';
import RestService from '../services/RestService';
import Header from './Header';
import { Stack, Button, TextField, MenuItem } from '@mui/material'
import SearchIcon from '@mui/icons-material/Search';
import { useNavigate } from 'react-router-dom';
import LoginIcon from '@mui/icons-material/Login';

function BookList() {
  const [search, setSearch] = useState("");
  const [bookData, setData] = useState([]);
  const navigate = useNavigate();
  const [value, setValue] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  const searchBook = (evt) => {
    evt.preventDefault();
    RestService.getBooks(search, value)
      .then((res) => {
        console.log(res);
        if (res.data.items != null) {
          setData(res.data.items)
        }
        else {
          setErrorMsg('No Books Found')
          console.log(bookData.length);
        }
      })
      .catch(err => console.log(err));
  }

  const getBooks = () => {
    RestService.getBooks('React', value)
      .then((res) => {
        if (res.status === 200) {
          console.log(res.data.items)
          setData(res.data.items)
        }
      })
      .catch(err => console.log(err));
  };

  useEffect(() => {
    getBooks();
  }, [])

  const login = () => {
    navigate('/');
  }



  return (
    <>
      {sessionStorage.getItem('username') ?
        <>
          < Header />
          <div className="row search-section container">
            <Stack spacing={3} direction='row' className="searchbar">
              <TextField label='Select Category' select value={value}
                onChange={(e) => setValue(e.target.value)} className='category'>
                <MenuItem value='Latest'>Latest</MenuItem>
                <MenuItem value='Drama'>Drama</MenuItem>
                <MenuItem value='Fiction'>Fiction</MenuItem>
                <MenuItem value='Comedy'>Comedy</MenuItem>
                <MenuItem value='Philosophy'>Philosophy</MenuItem>
                <MenuItem value='Horror'>Horror</MenuItem>
                <MenuItem value='Thriller'>Thriller</MenuItem>
                <MenuItem value='Art'>Art</MenuItem>
                <MenuItem value='Science'>Science</MenuItem>
                <MenuItem value='Top-Rated'>Top-Rated</MenuItem>
              </TextField>
              <TextField label='Search' placeholder="Enter Your Book Name"
                value={search} onChange={e => setSearch(e.target.value)} />
              <Button variant='contained' startIcon={<SearchIcon />} onClick={searchBook}>Search</Button>
            </Stack>
          </div>

          {errorMsg ? <div className='container'>{errorMsg}</div> :
            <div className="container">
              <div className="row">
                {
                  <Card book={bookData} />
                }
              </div>
            </div>
          }
        </>
        : <div className='container'>
          <h1>Please Login</h1>
          <Button variant='contained' startIcon={<LoginIcon />} onClick={login}>Login</Button>
        </div>
      }
    </>
  )
}

export default BookList
