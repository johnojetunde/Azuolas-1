import styled from 'styled-components'

export const Container = styled.div`
    display: block;
`
export const Convo = styled.div`
    height: 400px;
    border-bottom: 0.5px solid rgba(0,0,0,0.4);
    padding: 30px;
    overflow-y: scroll;
`
export const MsgContainer = styled.div`
    display: flex;
    justify-content: ${ props => props.user ? null : "flex-end" };
    width: 50%;
`;
export const Msg = styled.div`
    background-color: ${props => (props.user ? "#6200ee" : "#ddd")};
    color: ${props => (props.user ? "#fff" : "#222")};
    padding: 10px;
    border-radius: ${ props => props.user ? "10px 10px 10px 0" : "10px 10px 0 10px" }; 
`;