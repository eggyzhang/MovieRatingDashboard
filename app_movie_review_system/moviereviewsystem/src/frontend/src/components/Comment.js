import React from 'react';
import { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from 'react-bootstrap';
import { MdCreate } from 'react-icons/md';
import { MdDelete } from 'react-icons/md';
import Button from 'react-bootstrap/Button'
import axios from 'axios';

class Comment extends Component {

    handleDelete = async (e) => {
        e.preventDefault();
        const {comment, updateComments} = this.props;

        console.log(comment.id)

        const headers = {
            'Access-Control-Allow-Origin': '*'
        }

        const response = await axios.delete(`http://localhost:8080/comment/delete/${comment.id}`, {mode:'cors'})

        updateComments(comment.id);

    }; 


    render(){
        const {comment} = this.props;

        return (            
            <Container className="rounded" style={{height: '6rem', background: '#eeeeee', borderLeft: '4px solid gray'}}>
                <div style={{ paddingTop: '0.25rem', fontSize: '14px', color: 'gray'}}>
                    {comment.userId}
                </div>
                <div style={{marginTop: '0.25rem'}}>
                    {comment.comment}
                </div >
                <div style={{flex:1, display: 'flex', justifyContent: 'flex-end'}}>
                    <Button variant='light 'type='submit' style={{height: '2rem', width: '2rem', marginRight: '0.5rem'}}>
                        <MdCreate style={{height: '1rem'}}/>
                    </Button>
                    <Button variant='light 'type='submit' onClick={this.handleDelete}
                        style={{height: '2rem', width: '2rem'}}>
                        <MdDelete style={{height: '1rem'}}/>
                    </Button>
                </div>
                
            </Container>
    

        );
    }
}

export default Comment;
