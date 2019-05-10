import React from "react";
import { Switch, Route } from "react-router-dom";
import Home from './pages/Home'
import Login from './pages/Login'
import "@material/button/dist/mdc.button.css";
import "@material/theme/dist/mdc.theme.css";

class App extends React.Component {
    render(){
        return (
            <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/login" component={Login} />
            </Switch>
        );
    }
}

export default App;
