import React from 'react';
import { Component } from 'react';
import MovieCard from './MovieCard';
import axios from 'axios';
import _ from "lodash";
import MoviePopup from './MoviePopup';
import MovieNavbar from './MovieNavbar';



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
        // const responseUser = await axios.get(`http:/localhost:8080/user/find/${userId}`, {mode:'cors'})
        this.setState({movies: _.get(response, "data")});

        // console.log(response2)
        
    }

    onMovieClick = ({movieId}) => {
        this.setState({activeMovieId: movieId, showingMoviePopup: true});
    }

    onMoviePopupHide = () => {
        this.setState({activeMovieId: null, showingMoviePopup: false})
    }

    onAvgRatingUpdate = ({movieId, avgRating}) => {
        var {movies} = this.state;
        _.set(movies, `[${_.findIndex(movies, {id: movieId})}].avgRating`, avgRating);
        this.setState({movies})
    }

    render(){
        
        const {showingMoviePopup, activeMovieId, movies} = this.state;
        console.log(this.state.movies);
        console.log(activeMovieId, showingMoviePopup)
        return (
            <div style={{paddingBottom: '3rem'}}> 
                <MovieNavbar userId={userId}/>
                <div className="container" style={{flex:1, display: 'flex', flexWrap: 'wrap',
                justifyContent: 'center'}}>
                    {_.map(movies, (movie) => {
                        return (<MovieCard {...{movie, onMovieClick: this.onMovieClick, key: movie.id}}/>)
                    })}
                </div>
                {showingMoviePopup && activeMovieId && <MoviePopup 
                    onMoviePopupHide={this.onMoviePopupHide}
                    onAvgRatingUpdate={this.onAvgRatingUpdate} 
                    movie={_.find(movies, {id: activeMovieId})}
                    userId={userId}
                />}
            </div>  
        );
    }


}

export default MovieDashboard;
