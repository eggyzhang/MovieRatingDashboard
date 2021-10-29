import React from 'react';
import { Component } from 'react';
import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactStars from 'react-stars';
import _ from "lodash";


class MovieCard extends Component {
    render(){
        const {movie} = this.props;
        return (            
            <Card className="shadow p-1 bg-white" onClick={() => this.props.onMovieClick({movieId: movie.id})} 
            style={{ width: '18rem', height: '34rem', marginTop: '3rem', marginBottom: '0rem',
            marginLeft:'1rem', marginRight:'1rem'}}>
                <Card.Img variant="top" src={movie.imageUrl} style={{ height:'25.5rem' }}/>
                <Card.Body style={{ height: '30rem'}}>
                    <Card.Title style={{height: '2rem', marginBottom: '1rem'}}><div>{movie.title}</div></Card.Title>
                    <Card.Text >
                        <div>
                            <ReactStars count={5} edit={false} value={movie.avgRating} size={28}/>
                        </div>
                    </Card.Text>
                </Card.Body>
            </Card>
        );
    }
}

export default MovieCard;
