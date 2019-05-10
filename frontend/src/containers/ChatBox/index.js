import React, { Component } from 'react'
import Input from '../../components/Input';
import ChatMsg from './ChatMsg'
import * as Chat from './style'

export default class ChatBox extends Component {
    state = {
        user: '',
        conversation: []
    }

    handleChange = e =>{
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = e =>{
        e.preventDefault()
        const {user, conversation} = this.state
        const botMsg = {
            userType: "bot",
            userName: "Bot",
            content: "Hi, there! I'm Azuolas."
        };
        const userMsg = {
            userType: "user",
            userName: "User",
            content: user
        };
        console.log(userMsg)
        console.log(conversation)
        const newMessage = conversation.push(userMsg)

        this.setState({
            bot: newMessage
        });

        setTimeout(() => {
            const newMessage = conversation.push(botMsg)
            this.setState({
                bot: newMessage
            });
        }, 500);
    }

    render() {
        const { conversation } = this.state

        return (
            <Chat.Container>
                <Chat.Convo>
                    {
                        conversation.map((message, i) => {
                            return <ChatMsg type={message.userType} message={message.content} key={i}/>;
                        })
                    }
                </Chat.Convo>
                <form onSubmit={this.handleSubmit}>
                    <Input
                        name="user"
                        placeholder="Say Hi!"
                        onChange={this.handleChange}
                    />
                </form>
            </Chat.Container>
        );
    }
}
