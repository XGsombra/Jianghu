import React from 'react';
import "../styles/home.css"

function showForm(e) {
    document.getElementById("welcome").style.display = "none";
    document.getElementById("hint").style.display = "none";
    document.getElementById("login-form").style.display = "flex"
    document.getElementById("register-link").style.display = "flex"
    document.getElementById("declaration").style.display = "flex"
}

const Home = () => {
    return (
        <div className="Home" onClick={showForm}>
            <body className="Home-body">
                <div id="welcome">
                    WELCOME TO
                </div>
                <div id="jianghu">
                    JIANG HU
                </div>

                <div id="login-form">
                    <div className="input">Account</div>
                    <input id="phone-email" placeholder="Enter email or phone number" type="text"></input>
                    <div className="input">Password</div>
                    <input id="password" placeholder="Enter your password" type="text"></input>
                    <a className="log-link" href="https://www.4399.com">Term of Service and Privacy Policy</a>
                    <button id="login-btn">Log In</button>
                </div>

                <a className="log-link" id="register-link" href="register">Register Account</a>

                <div id="hint">
                    CLICK ANYWHERE TO CONTINUE
                </div>

                <div id="declaration">
                    By registering an account, you accept the Term of Service and Privacy Policy
                </div>
            </body>
        </div >
    );
}; export default Home;
