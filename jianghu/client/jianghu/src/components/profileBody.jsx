import React from 'react';
import '../styles/profile.css';
import UserProfile from './userProfile';

class ProfileBody extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange = function (e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    render() {
        return (
            <div id="profile-body">
                <UserProfile />
            </div>
        );
    };
}

export default ProfileBody;