import React from 'react';
import "../styles/register.css"
let ajax = require("../api/ajax");


function register(e) {
    let username = document.getElementById("username").nodeValue;
    let email = document.getElementById("email").nodeValue;
    let password = document.getElementById("password").nodeValue;
    let phone = document.getElementById("phone").nodeValue;
    ajax.send("POST", "/api/users/signup", { username, email, password, phone }, console.log);
}

const Register = () => {
    return (
        <div className="Register">
            <body className="Register-body">
                <div id="register">
                    Register Account
              </div>
                <div id="register-form">
                    <div className="input">Username</div>
                    <input id="username" placeholder="Enter your username" type="text"></input>
                    <div className="input">Phone Number</div>
                    <input id="phone" placeholder="Enter phone number" type="text"></input>
                    <div className="input">Email Address</div>
                    <input id="address" placeholder="Enter email address" type="text"></input>
                    <div className="input">Password</div>
                    <input id="password" placeholder="Enter your password" type="text"></input>
                    <div className="input">Confirm Password</div>
                    <input id="password" placeholder="Enter your password again" type="text"></input>
                    <button id="register-btn" onClick={register}>Register</button>
                </div>

            </body>
        </div >
    );
}; export default Register;
