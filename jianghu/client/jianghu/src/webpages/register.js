import React from 'react';
import "../styles/register.css"
import RegisterForm from "../components/registerForm"
import Header from "../components/header.jsx"

const Register = () => {
    return (
        <div className="Register">
            <Header />
            <div className="Register-body">

                <RegisterForm />

            </div>
        </div >
    );
};

export default Register;
