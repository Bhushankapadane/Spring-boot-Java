import React,{ useState, useEffect } from 'react'
import CloseIcon from '@mui/icons-material/Close';
import { Button } from '@mui/material'
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import StarBorderIcon from '@mui/icons-material/StarBorder';
import RecommendIcon from '@mui/icons-material/Recommend';
import RestService from '../services/RestService';
import { useNavigate } from 'react-router-dom';
import { TextField } from '@mui/material'
import ForumIcon from '@mui/icons-material/Forum';

function Modal({ show, item, onClose }) {
  const navigate = useNavigate();
  const [userComment, setComment] = useState('');
  const [commentData, setCommentdata] = useState([]);
  const bookId = item.id;

  const getComment = () => {
    RestService.getComment(bookId)
      .then((res) => {
        if (res.status === 200) {
          console.log(res)
          let comment = res.data;
          console.log(comment);
          setCommentdata(comment);
        }
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    getComment();
  }, [])

  const addComment = (e) => {
    e.preventDefault();
    let comment1 = {
      bookId: bookId,
      usercomment: userComment,
      username: sessionStorage.getItem('username')
    }
    console.log(JSON.stringify(comment1));
    RestService.addComment(comment1)
      .then((res) => {
        console.log(res);
        if (res.status === 201) {
          getComment();
        }
      })
  }

  const deleteComment = (commentId) => {
    RestService.deleteComment(commentId)
      .then((res) => {
        console.log(commentId, res);
        console.log("Delete data", commentData);
        RestService.getComment(bookId)
          .then((res) => {
            if (res.status === 200) {
              let comment = res.data;
              console.log(comment);
              setCommentdata(comment);
            }
          })
          .catch((err) => console.log(err));
      })
  }

  if (!show) {
    return null;
  }

  const favouriteBtnClick = () => {
    console.log('favourite')
    let object = {
      bookId: item.id,
      bookTitle: item.volumeInfo.title,
      bookAuthors: item.volumeInfo.authors,
      bookPublisher: item.volumeInfo.publisher,
      bookPublishedDate: item.volumeInfo.publishedDate,
      bookDescription: item.volumeInfo.description,
      bookCategory: item.volumeInfo.categories && item.volumeInfo.categories[0] ? item.volumeInfo.categories[0] : "",
      bookImage: item.volumeInfo.imageLinks.thumbnail,
      bookLanguage: item.volumeInfo.language,
      username: sessionStorage.getItem('username'),
    }

    RestService.addBookTofavourites(object)
      .then(res => {
        console.log(res)
        if (res.status === 201) {
          alert('Favourite added Successfully')
        }
      })
      .catch(err => {
        console.log(err)
        alert('Already added to Favourites');
      });
  }

  const recommendedBtnClick = () => {
    let object = {
      bookId: item.id,
      bookTitle: item.volumeInfo.title,
      bookAuthors: item.volumeInfo.authors,
      bookPublisher: item.volumeInfo.publisher,
      bookPublishedDate: item.volumeInfo.publishedDate,
      bookDescription: item.volumeInfo.description,
      bookCategory: item.volumeInfo.categories && item.volumeInfo.categories[0] ? item.volumeInfo.categories[0] : "",
      bookImage: item.volumeInfo.imageLinks.thumbnail,
      bookLanguage: item.volumeInfo.language,
      username: sessionStorage.getItem('username')
    }
    RestService.addBookTorecommended(object)
      .then((res) => {
        if (res.status === 201) {
          alert('Book Recommended Successfully');
        }
      })
      .catch(err => {
        console.log(err);
        alert('Already Recommended');
      });
  }

  let thumbnail = item.volumeInfo.imageLinks && item.volumeInfo.imageLinks.smallThumbnail;
  let previewLink = item.volumeInfo.previewLink;

  return (
    <>
      <div className="container">
        <img src={thumbnail} alt="" />
        <div className="info">
          <h1>Title : {item.volumeInfo.title}</h1>
          <h3>Authors : {item.volumeInfo.authors}</h3>
          <h4>Publisher : {item.volumeInfo.publisher}&nbsp;</h4>
          <h4>Published Date : {item.volumeInfo.publishedDate}</h4>
          <Button onClick={() => window.open(previewLink, "_blank")}
            variant='contained' startIcon={<MoreHorizIcon />}>More</Button>
        </div>
        <h2>Description</h2><p className="description">{item.volumeInfo.description}</p>

        <h2 className="text-center">User Comments</h2>
        <div className="table-responsive-sm">
        <div className="row">
          <table className="table table-striped table-hover table-sm commentTable">
            <thead>
              <tr>
                <th>UserName</th>
                <th>Comments</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {commentData && commentData.map((comment) => {
                return (
                  <tr key={comment.commentId}>
                    <td>{comment.username}</td>
                    <td>{comment.usercomment}</td>
                    {sessionStorage.getItem('username') === comment.username ?
                      <td>
                        <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => {
                          deleteComment(comment.commentId)
                        }}>Delete</button>
                      </td> : <td></td>}
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
        </div>
        <TextField label='Add Comment' size='small' value={userComment}
          onChange={(e) => setComment(e.target.value)} /> &nbsp;

        <Button variant='contained' color='success' onClick={addComment} startIcon={<ForumIcon/>}>Add Comment</Button>
        <br />
        <br />
        <Button variant='contained' color='warning' startIcon={<StarBorderIcon />} onClick={favouriteBtnClick}>Add to Favourite</Button> &nbsp;
                <Button variant='contained' startIcon={<RecommendIcon />} onClick={recommendedBtnClick}>Recommend Book</Button><br />
        <br />
        <Button variant='contained' color='error' startIcon={<CloseIcon />} onClick={onClose}>Close</Button>
      </div>
    </>
  )
}

export default Modal
