import React, { useEffect, useState } from 'react';
import endpoint from './../config/endpoint.json';
import text from './../lang/tr.json';
import CircularProgressBar from './tools/CircularProgressBar';



function Statistics() {

    const [userCount, setUserCount] = useState();
    const [toFill, setToFill] = useState();
    const [percentage, setPercentage] = useState(0);

    const [yesCount, setYesCount] = useState();
    const [noCount, setNoCount] = useState();
    const [perYesNo, setPerYesNo] = useState(0);

    useEffect(() => {
        fetch(endpoint["getFilling"], {
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json"
            }
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                setUserCount(response.userCount);
                setToFill(response.toFill);
                setPercentage(parseInt((100 * response.toFill) / response.userCount))
            }).catch(err => {
                console.log(err);
            });

        fetch(endpoint["getAnswer"], {
            "method": "GET",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json"
            }
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                setYesCount(response.yes);
                setNoCount(response.no);
                setPerYesNo(parseInt((100 * response.yes) / (response.yes + response.no)))
            }).catch(err => {
                console.log(err);
            });
    }, []);

    return (
        <div className="content-sta">
            <div className="statistic-content">
                <p className="statistic-title">{text['user-filling']}</p>
                <span className="statistic-desc">{text['user-count']}<span className="statistic-value">{userCount}</span></span><br />
                <span className="statistic-desc">{text['user-filling-count']}<span className="statistic-value">{toFill}</span></span>
                <CircularProgressBar
                    strokeWidth="10"
                    sqSize="200"
                    percentage={percentage} />
            </div>

            <div className="statistic-content">
                <p className="statistic-title">{text['satisfaction-ratio']}</p>
                <span className="statistic-desc">{text['yes-count']}<span className="statistic-value">{yesCount}</span></span><br />
                <span className="statistic-desc">{text['no-count']}<span className="statistic-value">{noCount}</span></span>
                <CircularProgressBar
                    strokeWidth="10"
                    sqSize="200"
                    percentage={perYesNo} />
            </div>
        </div>)
}
export default Statistics;