import React from 'react';
import LoginForm from "../components/loginForm.jsx"
import "../styles/login.css"


const Login = () => {
    return (
        <div className="Login">
            <div className="Login-body">
                <div id="jianghu">
                    JIANG HU
                </div>

                <LoginForm />

                <a id="register-link" href="register">Register Account</a>

                <div id="declaration" className="footer">
                    By registering an account, you accept the Term of Service and Privacy Policy
                </div>

                <div id="copyright" className="footer">
                    Copyright © 2021 Xuduo Gu
                </div>
            </div>
        </div >
    );
};

export default Login;
