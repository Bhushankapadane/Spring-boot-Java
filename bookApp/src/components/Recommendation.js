import { useState, useEffect } from "react";
import RestService from "../services/RestService";
//import axios from "axios";
import Header from './Header';
import { Stack, Button, TextField } from '@mui/material'
import SearchIcon from '@mui/icons-material/Search';

function Recommendation() {
  const [recommended, setrecommended] = useState([]);
  const [noRecommend,setNoRecommend] = useState('');

  const getRecommendedBook = () => {
    RestService.getRecommendedBook()
      .then((response) => {
        console.log(response);
        if(response.status===200) {
          setrecommended(response.data);
        }
        else if(response.status===204) {
          setNoRecommend('No Recommended Books Found');
        }
      })
      .catch((err) => console.log(err));
  };



  useEffect(() => {
    getRecommendedBook();
  }, []);

  return (
    <>
      <Header />
      <div className="search-section">
        <Stack spacing={2} direction='row' className="searchbar">
                <TextField label='Search' placeholder="Enter Your Book Name" />
                <Button variant='contained' startIcon={<SearchIcon />} >Search</Button>
        </Stack>
      </div>
      { noRecommend ? <h1> No Recommended Books Found</h1> :
      <div className="container">
        <div className="row">
          {recommended &&
            recommended.length > 0 &&
            recommended.map((item) => {
              return (
                <div className="col" key={item.bookId}>
                  <div className="card">
                    <img src={item.bookImage} className="card-img-top" alt="" />
                    <div className="card-body">
                      <p className="card-title" >{item.bookTitle}</p>
                    </div>
                  </div>
                </div>
              )
            })}
        </div>
      </div>
    }
    </>
  );
}

export default Recommendation;
