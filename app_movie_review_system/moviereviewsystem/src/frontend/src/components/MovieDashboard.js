import React from 'react';
import { Component } from 'react';
import MovieCard from './MovieCard';
import axios from 'axios';
import _ from "lodash";
import { Container } from 'react-bootstrap';
import MoviePopup from './MoviePopup';
import { Modal } from 'react-bootstrap';
import { Navbar } from 'react-bootstrap';


const userId = 1;

class MovieDashboard extends Component {
    state = {
        movies: []
    }
    async componentDidMount(){
        const headers = {
            'Access-Control-Allow-Origin': '*'
        }
        
        const response = await axios.get('http://localhost:8080/movie/all', {mode:'cors'})
        this.setState({movies: _.get(response, "data")});
        
    }

    onMovieClick = ({movieId}) => {
        this.setState({activeMovieId: movieId, showingMoviePopup: true});
    }

    onMoviePopupHide = () => {
        this.setState({activeMovieId: null, showingMoviePopup: false})
    }

    render(){
        
        const {showingMoviePopup, activeMovieId, movies} = this.state;
        console.log(this.state.movies);
        console.log(activeMovieId, showingMoviePopup)
        return (
            <div>
                <Navbar bg="dark" variant="dark">
                    <Container>
                    <Navbar.Brand href="#home">
                    Movie Ratings
                    </Navbar.Brand>
                    <Navbar.Toggle />
                    <Navbar.Collapse className="justify-content-end">
                    <Navbar.Text>
                        Signed in as: <a href="#login">User {userId}</a>
                    </Navbar.Text>
                    </Navbar.Collapse>
                    </Container>
                </Navbar>
                <div className="container" style={{flex:1, display: 'flex', flexWrap: 'wrap',
                justifyContent: 'center'}}>
                    {_.map(movies, (movie) => {
                        return (<MovieCard {...{movie, onMovieClick: this.onMovieClick, key: movie.id}}/>)
                    })}
                </div>
                {showingMoviePopup && activeMovieId && <MoviePopup 
                    onMoviePopupHide={this.onMoviePopupHide} 
                    movie={_.find(movies, {id: activeMovieId})}
                    userId={userId}
                />}

                
        
            </div>  
        );
    }


}

export default MovieDashboard;
