import React from 'react';
import '../styles/header.css';

class Header extends React.Component {
    render() {
        return (
            <div id="header">
                <div className="clickable">JIANGHU</div>
            </div>
        );
    };
}

export default Header;