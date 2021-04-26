import React from 'react';
import '../styles/login.css';

class LoginForm extends React.Component {
    render() {
        return (
            <div id="login-form">
                <div className="input">Account</div>
                <input id="phone-email" placeholder="Enter email or phone number" type="text"></input>
                <div className="input">Password</div>
                <input id="password" placeholder="Enter your password" type="text"></input>
                <a className="log-link" href="https://www.4399.com">Term of Service and Privacy Policy</a>
                <button id="login-btn">Log In</button>
            </div>
        );
    };
}

export default LoginForm;