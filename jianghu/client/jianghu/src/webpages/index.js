import React from 'react';
import {
    BrowserRouter as Router,
    Route,
} from "react-router-dom";
import Login from './login';
import Register from './register';
import Home from './home';
import Favorites from './favorites';

const styleLink = document.createElement("link");
styleLink.rel = "stylesheet";
styleLink.href = "https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css";
document.head.appendChild(styleLink);

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