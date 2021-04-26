import React from 'react';
import '../styles/register.css';

class RegisterForm extends React.Component {

    changeToEmail = function (e) {
        document.getElementById("phone-div").style.display = "none";
        document.getElementById("email-div").style.display = "block";
        document.getElementById("register-phone-btn").style.display = "block";
        document.getElementById("register-email-btn").style.display = "none";
    }

    changeToPhone = function (e) {
        document.getElementById("phone-div").style.display = "block";
        document.getElementById("email-div").style.display = "none";
        document.getElementById("register-phone-btn").style.display = "none";
        document.getElementById("register-email-btn").style.display = "block";
    }

    render() {
        return (
            <div id="register-form">
                <div className="input">Username</div>
                <input id="username" placeholder="Enter your username" type="text"></input>
                <div id="phone-div">
                    <div className="input">Phone Number</div>
                    <input id="phone" placeholder="Enter phone number" type="text"></input>
                </div>
                <div id="email-div">
                    <div className="input">Email Address</div>
                    <input id="email" placeholder="Enter email address" type="text"></input>
                </div>
                <div className="input">Password</div>
                <input id="password" placeholder="Enter your password" type="text"></input>
                <div className="input">Confirm Password</div>
                <input id="password" placeholder="Enter your password again" type="text"></input>
                <button className="register-btns" id="register-btn">Register</button>
                <button className="register-btns" id="register-email-btn" onClick={this.changeToEmail}>Register using email</button>
                <button className="register-btns" id="register-phone-btn" onClick={this.changeToPhone}>Register using phone</button>
            </div >
        );
    };
}

export default RegisterForm;