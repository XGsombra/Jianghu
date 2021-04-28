import React from 'react';
import '../styles/profile.css';

class UserProfile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            phone: "",
            level: 0,
            password: "",
            passwordConfirm: ""
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleSave = this.handleSave.bind(this);
        this.handleLoad = this.handleLoad.bind(this);
    }

    componentDidMount() {
        window.addEventListener('load', this.handleLoad);
    }

    handleLoad = function (e) {
        const { username } = this.state;
        console.log(username);
        console.log(localStorage.getItem("userId"));
        this.setState({ [username]: localStorage.getItem("userId") })
    }

    handleEdit = function (e) {
        document.getElementById("user-info-edit").style.display = "flex";
        document.getElementById("user-info").style.display = "none";
        document.getElementById("save-btn").style.display = "block";
        document.getElementById("edit-btn").style.display = "none";
    }

    handleSave = function (e) {
        // TODO: update frist
        document.getElementById("user-info-edit").style.display = "none";
        document.getElementById("user-info").style.display = "flex";
        document.getElementById("save-btn").style.display = "none";
        document.getElementById("edit-btn").style.display = "block";
    }

    handleInputChange = function (e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    render() {
        return (
            <div id="user-profile">
                <div id="user-info" className="user-table">
                    <div className="labels">
                        <label className="profile-field-title">Username</label>
                        <label className="profile-field-title">Email Address: </label>
                        <label className="profile-field-title">Phone Number:  </label>
                        <label className="profile-field-title">Level:         </label>
                    </div>
                    <div id="contents">
                        <div className="profile-field-content">{this.state.username}</div>
                        <div className="profile-field-content">{this.state.email}</div>
                        <div className="profile-field-content">{this.state.phone}</div>
                        <div className="profile-field-content">{this.state.level}</div>
                    </div>
                </div>
                <div id="user-info-edit" className="user-table">
                    <div className="labels">
                        <label className="profile-field-title">Username</label>
                        <label className="profile-field-title">Email Address: </label>
                        <label className="profile-field-title">Phone Number:  </label>
                        <label className="profile-field-title">New Password:  </label>
                        <label className="profile-field-title">Confirm Password:</label>
                    </div>
                    <div id="contents">
                        <input className="profile-field-edit" name="username" value={this.state.username} onChange={this.handleInputChange} />
                        <input className="profile-field-edit" name="email" value={this.state.email} onChange={this.handleInputChange} />
                        <input className="profile-field-edit" name="phone" value={this.state.phone} onChange={this.handleInputChange} />
                        <input className="profile-field-edit" name="password" placeholder="Enter new password" onChange={this.handleInputChange} />
                        <input className="profile-field-edit" name="passwordConform" placeholder="Enter new password again" onChange={this.handleInputChange} />
                    </div>
                </div>
                <div id="btns">
                    <div className="profile-btn" id="edit-btn" onClick={this.handleEdit}>Edit</div>
                    <div className="profile-btn" id="save-btn" onClick={this.handleSave}>Save</div>
                </div>
            </div >
        );
    };
}

export default UserProfile;