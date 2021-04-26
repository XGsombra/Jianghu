import React from 'react';
import '../styles/register.css';
import { Form } from 'semantic-ui-react';
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

    handleRegister = () => {
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
        registerUser(username, email, phone, password, console.log, console.log);
    };

    handleInputChange = function (e, { name, value }) {
        this.setState({ [name]: value })
    };

    render() {
        return (
            <Form id='register-form' onSubmit={this.handleRegister} >
                <Form.Input
                    label='Username'
                    placeholder="Enter your username"
                    name='username'
                    className="input"
                    onChange={this.handleInputChange}
                    required
                />
                <Form.Input
                    label='Email'
                    placeholder='Enter your email address'
                    name='email'
                    className="input"
                    onChange={this.handleInputChange}
                    disabled={this.state.phone !== ""}
                    required
                />
                <Form.Input
                    label='Phone'
                    placeholder='Enter your phone number'
                    name='phone'
                    className="input"
                    onChange={this.handleInputChange}
                    disabled={this.state.email !== ""}
                    required
                />
                <Form.Input
                    label='Password'
                    type='password'
                    placeholder="Enter your password"
                    name='password'
                    className="input"
                    onChange={this.handleInputChange}
                    required
                />
                <Form.Input
                    label='Password Confirmation'
                    placeholder="Enter your password again"
                    type='password'
                    name='passwordConfirm'
                    className="input"
                    onChange={this.handleInputChange}
                    required
                />
                <Form.Button
                    fluid
                    id="register-btns"
                    type='submit'
                >Register</Form.Button>
            </Form>
        );
    };
}

export default RegisterForm;