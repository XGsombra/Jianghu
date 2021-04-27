import React from 'react';
import '../styles/register.css';
import { registerUser } from '../api/userApi';

class RegisterForm extends React.Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            passwordConfirm: "",
            email: "",
            phone: "",
        };
        this.handleRegister = this.handleRegister.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleRegister = function () {
        const { username, password, passwordConfirm, email, phone } = this.state;
        console.log(username);
        if (email !== "" && !email.includes("@")) {
            alert("Invalid email address");
            return;
        }
        if (phone !== "" && isNaN(phone)) {
            alert("Invalid phone address");
            return;
        }
        if (password !== passwordConfirm) {
            alert("Password doesn't match, please try again");
            return;
        }
        registerUser(username, email, phone, password,
            function (res) {
                localStorage.setItem("userId", res.data.userId);
                window.location.href = "/";
            }, console.log);
    };

    handleInputChange = function (e) {
        this.setState({ [e.target.name]: e.target.value })
    };

    render() {
        return (
            <div id="register-form">
                <div id="inputs">
                    <div className="input-title">Username</div>
                    <input
                        id="username"
                        className="input"
                        placeholder="Enter your username"
                        type="text"
                        name="username"
                        value={this.state.username}
                        onChange={this.handleInputChange} />
                    <div className="input-title">Phone Number</div>
                    <input
                        id="phone"
                        className="input"
                        placeholder="Enter your phone number"
                        type="text"
                        name="phone"
                        value={this.state.phone}
                        onChange={this.handleInputChange}
                        disabled={this.state.email !== ""} />
                    <div className="input-title">Email Address</div>
                    <input
                        id="email"
                        className="input"
                        placeholder="Enter your email address"
                        type="text"
                        name="email"
                        value={this.state.email}
                        onChange={this.handleInputChange}
                        disabled={this.state.phone !== ""} />
                    <div className="input-title">Password</div>
                    <input
                        id="password"
                        className="input"
                        placeholder="Enter your password"
                        type="password"
                        name="password"
                        value={this.state.password}
                        onChange={this.handleInputChange} />
                    <div className="input-title">Confirm Password</div>
                    <input
                        id="passwordConfirm"
                        className="input"
                        placeholder="Enter your password again"
                        type="password"
                        name="passwordConfirm"
                        value={this.state.passwordConfirm}
                        onChange={this.handleInputChange} />
                </div>
                <button id="register-btn" onClick={this.handleRegister}>Register</button>
            </div >
        );
    };
}

export default RegisterForm;