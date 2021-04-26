import React from 'react';
import LoginForm from "../components/loginForm.jsx"
import "../styles/login.css"

function showForm(e) {
    document.getElementById("welcome").style.display = "none";
    document.getElementById("hint").style.display = "none";
    document.getElementById("login-form").style.display = "flex"
    document.getElementById("register-link").style.display = "flex"
    document.getElementById("declaration").style.display = "flex"
    document.getElementById("copyright").style.display = "flex"
}

const Login = () => {
    return (
        <div className="Login" onClick={showForm}>
            <body className="Login-body">
                <div id="welcome">
                    WELCOME TO
                </div>
                <div id="jianghu">
                    JIANG HU
                </div>

                <LoginForm />

                <a className="log-link" id="register-link" href="register">Register Account</a>

                <div id="hint">
                    CLICK ANYWHERE TO CONTINUE
                </div>

                <div id="declaration" className="footer">
                    By registering an account, you accept the Term of Service and Privacy Policy
                </div>

                <div id="copyright" className="footer">
                    Copyright © 2021 Xuduo Gu
                </div>
            </body>
        </div >
    );
};

export default Login;
