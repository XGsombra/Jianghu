import React from 'react';
import '../styles/login.css';
import { Form } from 'semantic-ui-react';
import { loginUser } from '../api/userApi';

class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.wrapper = React.createRef();
        this.state = {
            account: "",
            password: "",
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleLogin = function () {
        const { account, password } = this.state;
        loginUser(account, password,
            function (res) {
                window.location.href = "/home";
            },
            function (err) {
                if (err.response.status === 404) {
                    alert("User does not exist");
                } else if (err.response.status === 401) {
                    alert("Wrong password");
                } else {
                    alert("Something went wrong")
                }
            });
    };

    handleInputChange = function (e, { name, value }) {
        this.setState({ [name]: value })
    };

    render() {
        return (
            <Form id='login-form' onSubmit={this.handleLogin}>
                <Form.Group widths="equal">
                    <Form.Input
                        fluid
                        label='Account'
                        placeholder='Enter your email or phone number'
                        name='account'
                        className="input"
                        onChange={this.handleInputChange}
                        required
                    />
                    <Form.Input
                        fluid
                        label='Password'
                        type='password'
                        placeholder="Enter your password"
                        name='password'
                        className="input"
                        onChange={this.handleInputChange}
                        required
                    />
                    <a className="log-link" href="https://www.4399.com">Term of Service and Privacy Policy</a>
                    {/* <Form.Checkbox label="Term of Service and Privacy Policy"></Form.Checkbox> */}
                    <Form.Button
                        fluid
                        createRef
                        id="login-btn"
                        type='submit'
                    >Login</Form.Button>
                </Form.Group>

            </Form>
        );
    };
}

export default LoginForm;