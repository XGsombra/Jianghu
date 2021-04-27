import React from 'react';
import '../styles/profile.css';

class UserProfile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "Xiagou",
            email: "ut.com",
            phone: "647413312",
            level: 1
        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleSave = this.handleSave.bind(this);
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
                        <label className="profile-field-title">Level:         </label>
                    </div>
                    <div id="contents">
                        <input className="profile-field-edit" value={this.state.username} />
                        <input className="profile-field-edit" value={this.state.email} />
                        <input className="profile-field-edit" value={this.state.phone} />
                        <div className="profile-field-content">{this.state.level}</div>
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