import React from 'react';
import '../styles/header.css';

class Header extends React.Component {
    render() {
        return (
            <div id="header">
                <div className="clickable" onClick={(e) => window.location.href = "/home"}>JIANGHU</div>
            </div>
        );
    };
}

export default Header;