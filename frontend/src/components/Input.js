import React from 'react'
import styled from 'styled-components'
import { TextField } from "@rmwc/textfield";
import "@material/textfield/dist/mdc.textfield.css";
import "@material/floating-label/dist/mdc.floating-label.css";
import "@material/notched-outline/dist/mdc.notched-outline.css";
import "@material/line-ripple/dist/mdc.line-ripple.css";

const Input = styled(TextField)`
    input {
        padding-left: 30px !important;
    }
`;


export default ({...props}) => {
    return <Input fullwidth {...props} />;
}

