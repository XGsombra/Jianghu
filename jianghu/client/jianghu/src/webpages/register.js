import React from 'react';
import "../styles/register.css"
import RegisterForm from "../components/registerForm"

const Register = () => {
    return (
        <div className="Register">
            <body className="Register-body">
                <div id="register">
                    Earn a place in Jiang Hu
              </div>

                <RegisterForm />

            </body>
        </div >
    );
}; export default Register;
