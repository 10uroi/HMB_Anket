import React from 'react';

import Statistics from './../components/Statistics';
import text from './../lang/tr.json';

import logo from './../img/logo.png';
function AdminPage() {

    return (
        <div className="main-root">
            <a href="/" style={{top:"10px", right:"10px", fontSize:"14px", position:"absolute", textDecoration:"underline", color:"white"}}>{text["main-page"]}</a>
            <div className="header"><img alt="logo" src={logo} className="logo" width="300" height="105"/></div>
            <h4>{text["app-name"]}</h4>
            {<Statistics/>}
        </div>
    );
}

export default AdminPage;
