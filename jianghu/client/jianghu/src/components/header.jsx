import React from 'react';
import '../styles/header.css';

class Header extends React.Component {
    render() {
        return (
            <div id="header">
                <div className="title-btn" onClick={(e) => window.location.href = "/home"}>JIANGHU</div>
                <div className="profile-pic" onClick={(e) => window.location.href = "/profile"}></div>
            </div>
        );
    };
}

export default Header;