import React from 'react';
import '../styles/login.css';
import { Form } from 'semantic-ui-react';
import { loginUser } from '../api/userApi';

class LoginForm extends React.Component {

    constructor() {
        super();
        this.state = {
            account: "",
            password: "",
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleLogin = function (accoun) {
        const { account, password } = this.state;
        loginUser(account, password, console.log, console.log);
    };

    handleInputChange = function (e, { name, value }) {
        this.setState({ [name]: value })
    };

    render() {
        return (
            <Form id='login-form' onSubmit={this.handleLogin} >
                <Form.Input
                    label='Account'
                    placeholder='Enter your email or phone number'
                    name='account'
                    id="account"
                    className="input"
                    onChange={this.handleInputChange}
                    required
                />
                <Form.Input
                    label='Password'
                    type='password'
                    placeholder="Enter your password"
                    name='password'
                    id="password"
                    className="input"
                    onChange={this.handleInputChange}
                    required
                />
                <a className="log-link" href="https://www.4399.com">Term of Service and Privacy Policy</a>
                <Form.Button
                    fluid
                    id="login-btn"
                    type='submit'
                >Login</Form.Button>
            </Form>
        );
    };
}

export default LoginForm;