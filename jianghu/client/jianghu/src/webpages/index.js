import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import Login from './login';
import Register from './register';
import Home from './home';
import Favorites from './favorites';

const Webpages = () => {
    return (
        <Router>
            <Route exact path="/" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route path="/home" component={Home} />
            <Route path="/favorites" component={Favorites} />
        </Router>
    );
}; export default Webpages;