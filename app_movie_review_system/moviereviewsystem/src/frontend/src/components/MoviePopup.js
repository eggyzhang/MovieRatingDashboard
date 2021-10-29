import React, { Fragment } from 'react';
import { Component } from 'react';
import ReactStars from 'react-stars'
import Modal from 'react-bootstrap/Modal'
import s from './MoviePopup.styles'
import _ from 'lodash';
import axios from 'axios';
import Comment from './Comment.js'

class MoviePopup extends Component {
    state = {
        commentText: "",
        rating: 0.0,
        comments: [],
    };

    async componentDidMount(){
        const headers = {
            'Access-Control-Allow-Origin': '*'
        }

        const {movie, userId} = this.props;
        
        const responseMovies = await axios.get(`http://localhost:8080/rating/find/user/${userId}/movie/${movie.id}`, {mode:'cors'})
        const responseComments = await axios.get(`http://localhost:8080/comment/all/${movie.id}`, {mode:'cors'})
        // const responseRating = await axios.get(`http://localhost:8080/rating/find/movie/${movie.id}`, {mode:'cors'})
        console.log(responseMovies);
        console.log(responseComments);
        // console.log(responseRating);
        this.setState({rating: _.get(responseMovies, "data").rating, 
                        comments: _.get(responseComments, "data")});
        
    }            

    handleRatingChange = async (newRating) => {
        const {movie, userId, onAvgRatingUpdate} = this.props;
        this.setState({rating: newRating});
        
        const headers = {
            'Access-Control-Allow-Origin': '*'
        }

        const body = {
            rating: newRating,
            movieId: movie.id,
            userId: userId

        }

        console.log(body)
        
        await axios.put('http://localhost:8080/rating/update', body, {mode:'cors'})
        const response = await axios.get(`http://localhost:8080/rating/find/movie/${movie.id}`, {mode:'cors'})        
        onAvgRatingUpdate({movieId: movie.id, avgRating: response.data})
    };

    handleRatingDelete = async () => {
        const {movie, userId, onAvgRatingUpdate} = this.props;
        this.setState({rating: 0});

        const headers = {
            'Access-Control-Allow-Origin': '*'
        }

        const body = {
            rating: 0,
            movieId: movie.id,
            userId: userId

        }

        console.log(body)

        await axios.delete(`http://localhost:8080/rating/delete/user/${userId}/movie/${movie.id}`, body, {mode:'cors'})
        const response = await axios.get(`http://localhost:8080/rating/find/movie/${movie.id}`, {mode:'cors'})
        
        onAvgRatingUpdate({movieId: movie.id, avgRating: response.data})

    }

    handleCommentTextChange = (e) => {
        this.setState({commentText: e.target.value});

    };
    
    handleSubmit = async (e) => {
        e.preventDefault();
        const {movie, userId} = this.props;
        const timestamp = Date.now();

        const headers = {
            'Access-Control-Allow-Origin': '*'
        }

        const body = {
            comment: this.state.commentText,
            movieId: movie.id,
            userId: userId,
            timestamp: timestamp
        }

        console.log(body)

        const response = await axios.post('http://localhost:8080/comment/add', body, {mode:'cors'})

        this.setState({commentText: "", comments: [...this.state.comments, response.data]});

    };

    updateComments = (id) => {
        this.setState({comments: this.state.comments.filter(comment => comment.id !== id)});
    };


    render(){
        var {movie} = this.props;
        var {comments, rating, commentText, collectiveRating} = this.state;
    
        return (            
        <Modal size="lg" show={true} onHide={() => this.props.onMoviePopupHide()}>
            <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        {movie.title}
                        <div style={{fontSize: "16px"}}>{movie.releaseDate}</div>
                    </Modal.Title>
                    
                </Modal.Header>
                <Modal.Body>
                    <div style={{alignItems: "center", justifyContent: "center", display: 'flex', marginBottom: "1rem"}}>
                        <img style={{height: "30rem"}} src={movie.imageUrl}/>
                    </div>

                    {_.map(['plot', 'director', 'writers', 'genre', 'language'], propertyKey => {
                        return (
                        <div style={{...s.properties}} key={propertyKey}>
                            <b>{_.upperFirst(propertyKey)}:</b> {movie[propertyKey]}
                        </div>)
                    })}

                    <div style={{...s.properties, paddingBottom: '0rem', paddingTop: '0rem', display: 'flex', flexDirection: 'row',
                                alignItems: 'center'}}>
                        <b>Your Rating:</b>
                        <div style={{width:'8rem', margin: '1rem'}}><ReactStars count={5} edit={true} size={28} value={rating} onChange={this.handleRatingChange}/></div>
                        <input type="submit" value="Clear Rating" style={{marginRight: '4rem'}} onClick={this.handleRatingDelete}/>
                        <b>Collective Rating:</b>
                        <div style={{width:'8rem', margin: '1rem'}}><ReactStars count={5} edit={false} value={movie.avgRating} size={28}/></div>
                        
                    </div>
                    <div style={{borderBottom: '1px solid black', paddingBottom: '1rem', paddingTop: '1rem'}}>                        
                        <form onSubmit={this.handleSubmit}>
                            <textarea placeholder="Leave a review." value={commentText} onChange={this.handleCommentTextChange}
                                style={{width: '100%', minHeight: '5rem'}}/>
                            <input type="submit" value="Submit" style={{marginTop: '0.5rem'}}/>
                        </form>
                    </div>
                    <div style={{paddingBottom: '1rem', paddingTop: '1rem'}}>
                        <b>Reviews:</b>
                        {_.map(comments, comment => {
                            return (
                                <div style={{marginTop: '1rem'}}><Comment {...{comment, updateComments: this.updateComments, key: comment.id}}/></div>)                        
                        })}
                    </div>

                    

                </Modal.Body>
                <Modal.Footer>

                </Modal.Footer>                              
        </Modal>
        );
    }


}

export default MoviePopup;