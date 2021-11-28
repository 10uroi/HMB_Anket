import React from 'react';

import Welcome from './../components/Welcome';
import Survey from './../components/Survey';
import text from './../lang/tr.json';

import logo from './../img/logo.png';
function MainPage() {

    function getToken() {
        var link = window.location.search.substr(1).split("=");
        if (link[0] === "token") {
            return link[1];
        } else {
            return "";
        }
    }

    return (
        <div className="main-root">
            <a href="admin" style={{top:"10px", right:"10px", fontSize:"14px", position:"absolute", textDecoration:"underline", color:"white"}}>{text["admin-page"]}</a>
            <div className="header"><img alt="logo" src={logo} className="logo" width="300" height="105"/></div>
            <h4>{text["app-name"]}</h4>
            {getToken() === "" ? <Welcome/> : <Survey  token={getToken()}/>}
        </div>
    );
}

export default MainPage;
