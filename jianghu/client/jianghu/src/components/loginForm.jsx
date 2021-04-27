import React from 'react';
import '../styles/login.css';
import { loginUser } from '../api/userApi';

class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.wrapper = React.createRef();
        this.state = {
            account: "",
            password: "",
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleLogin = function () {
        const { account, password } = this.state;
        loginUser(account, password,
            function (res) {
                window.location.href = "/home";
            },
            function (err) {
                if (err.response.status === 404) {
                    alert("User does not exist");
                } else if (err.response.status === 401) {
                    alert("Wrong password");
                } else {
                    alert("Something went wrong")
                }
            });
    };

    handleInputChange = function (e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    render() {
        return (
            <div id="login-form">
                <div id="inputs">
                    <div className="input-title">Account</div>
                    <input
                        id="account"
                        className="input"
                        placeholder="Enter email or phone number"
                        type="text"
                        name="account"
                        value={this.state.account}
                        onChange={this.handleInputChange} />
                    <div className="input-title">Password</div>
                    <input
                        id="password"
                        className="input"
                        placeholder="Enter your password"
                        type="password"
                        name="password"
                        value={this.state.password}
                        onChange={this.handleInputChange} />
                </div>
                <a id="term-link" href="https://www.4399.com">Term of Service and Privacy Policy</a>
                <button id="login-btn" onClick={this.handleLogin}>Log In</button>
            </div>
        );
    };
}

export default LoginForm;