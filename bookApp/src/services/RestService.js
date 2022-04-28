import axios from "axios"

const BASE_URL = 'http://localhost:8082'
const FAV_URL = 'http://localhost:9005/api/bookApp'
const RECOMMEND_URL = 'http://localhost:8089/api/bookApp'
const COMMENT_URL = 'http://localhost:8989/api/bookApp'

class RestService {

    registerUser(user) {
        return axios.post(`${BASE_URL}/adduser`, user);
    }

    loginUser(user) {
        return axios.post(`${BASE_URL}/login`, user);
    }

    getBooks(search,category) {
        // var uri = `${BASE_URL}/books?search=${search}`;
        // if(category!==null && typeof category!==undefined && category.length!==0) {
        //     uri = `${BASE_URL}/books?search=${search}&category=${category}`;
        // }
        // console.log(uri);
        console.log(category);
        return axios.get(`${BASE_URL}/books?search=${search}&category=${category}`);
    }

    addBookTorecommended(book) {
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.post(`${RECOMMEND_URL}/addToRecommendedBooks`, book,{ headers: header });
    }

    getRecommendedBook() {
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.get(`${RECOMMEND_URL}/getAllRecommendedBooks`,{ headers: header });
    }

    getRecommendedBookbyusername(username) {
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.get(`${RECOMMEND_URL}/getMyRecommendedBooks/${username}`,{ headers: header });
    }

    unrecommend(id) {
        const token = sessionStorage.getItem('token');
        const username = sessionStorage.getItem('username');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.delete(`${RECOMMEND_URL}/unrecommend/${id}/${username}`,{ headers: header });
    }

    addBookTofavourites(book){
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.post(`${FAV_URL}/addToFavourites`,book,{ headers: header });
    }

    getFavouriteBook() {
        const token = sessionStorage.getItem('token');
        const username = sessionStorage.getItem('username');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.get(`${FAV_URL}/getFavourites/${username}`,{ headers: header });
    }

    deleteFavouriteBook(id) {
        const token = sessionStorage.getItem('token');
        const username = sessionStorage.getItem('username');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.delete(`${FAV_URL}/deleteFromFavourite/${id}/${username}`,{ headers: header });
    }

    addComment(comment1){
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.post(`${COMMENT_URL}/addComment`,comment1,{ headers: header })
    }

    getComment(bookId){
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.get(`${COMMENT_URL}/getComments/${bookId}`,{ headers: header })
    }

    deleteComment(commentId){
        const token = sessionStorage.getItem('token');
        const header = {
            "Authorization": `Bearer ${token}`,
            "Access-Control-Allow-Origin": "*"
        }
        return axios.delete(`${COMMENT_URL}/deleteComment/${commentId}`,{ headers: header })
    }

}

export default new RestService();