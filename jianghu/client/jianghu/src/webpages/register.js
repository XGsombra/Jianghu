import React from 'react';
import "../styles/register.css"
import RegisterForm from "../components/registerForm"
import Header from "../components/header.jsx"

const Register = () => {
    return (
        <div className="Register">
            <Header />
            <body className="Register-body">
                <div id="register">
                    Earn a place in Jiang Hu
              </div>

                <RegisterForm />

            </body>
        </div >
    );
}; export default Register;
