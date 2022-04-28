import { useState, useEffect } from "react";
import CardActions from "@mui/material/CardActions";
import DeleteIcon from "@mui/icons-material/Delete";
import RestService from "../services/RestService";
import Header from './Header';
import { Stack, Button, TextField } from '@mui/material'
import SearchIcon from '@mui/icons-material/Search';



function Favourites() {
  const [favourite, setfavourite] = useState([]);
  const [noFav,setNoFavourite] = useState('');

  const getFavouriteBook = () => {
    RestService.getFavouriteBook()
      .then((response)=> {
        if(response.status===200) {
          setfavourite(response.data);
        }
        else if(response.status===204) {
          setNoFavourite('No Favourites Found');
        }
      })
      .catch((err) => console.log(err));
  };

  const deleteFavouriteBook = async id => {
    RestService.deleteFavouriteBook(id)
      .then((response) => {
        console.log(response);
        if(response.status===200) {
          alert('Removed From Favourites');
          getFavouriteBook();
        }
      })
      .catch((err) => console.log(err));
  };


  useEffect(() => {
    getFavouriteBook();
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
      {noFav ? <h1> No Favourite Books Found</h1> :
      <div className="container">
        <div className="row">
          {favourite &&
            favourite.length > 0 &&
            favourite.map((item) => {
              return (
                <div className="col" key={item.bookId}>
                  <div className="card">
                    <img src={item.bookImage} className="card-img-top" alt="" />
                    <div className="card-body">
                      <p className="card-title">{item.bookTitle}</p>
                    </div>
                    <CardActions>
                      <Button variant="outlined" startIcon={<DeleteIcon />} onClick={() => deleteFavouriteBook(item.bookId)}>
                        Delete
                      </Button>
                    </CardActions>
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

export default Favourites;