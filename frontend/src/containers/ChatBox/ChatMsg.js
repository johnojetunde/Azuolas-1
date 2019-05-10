import React from "react";
import { MsgContainer, Msg } from "./style";

const ChatMsg = ({ type, message }) => {
    const msg = type === "user" ? true : false;
    return (
        <MsgContainer user={msg}>
            <Msg user={msg}>{message}</Msg>
        </MsgContainer>
    );
};

export default ChatMsg;
