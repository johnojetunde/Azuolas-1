import React, { Component } from 'react'
import AppBar from '../components/Appbar';
import ChatBox from '../containers/ChatBox';



export default class Home extends Component {
    render() {
        return (
            <div>
                <AppBar />
                <ChatBox />
            </div>
        )
    }
}
