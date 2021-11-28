import React, { useEffect, useState } from 'react';
import text from './../lang/tr.json';
import endpoint from './../config/endpoint.json';

function Welcome() {

    const [tokens, setTokens] = useState([]);

    useEffect(() => {

        fetch(endpoint["getTokens"], {
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json"
            }
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                setTokens(response);
            }).catch(err => {
                console.log(err);
            });

    }, []);

    return (
        <div className="content">
            <p>
                {text["bad-token"]}
            </p>
            <p>Deneme için aşağıdaki linkleri kullanabilirsiniz</p>
            <ul>
                {tokens.map((token,index) => (
                    <li key={index}><a href={"/?token="+token}>{"?token="+token}</a></li>
                ))}
            </ul>
        </div>)
}
export default Welcome;