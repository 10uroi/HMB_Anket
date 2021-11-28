import React, { useEffect, useState } from 'react';

import Button from './tools/Button';
import text from './../lang/tr.json';
import endpoint from './../config/endpoint.json';

function Survey(props) {

    const [questions, setQuestions] = useState([]);

    useEffect(() => {

        fetch(endpoint["getQuestions"], {
            "method": "POST",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json"
            },
            "body": JSON.stringify({
                token: props.token
            })
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                setQuestions(response.questions);
            }).catch(err => {
                window.location.href = "/"
                console.log(err);
            });

    }, []);

    function setAnswer() {
        fetch(endpoint["setAnswer"], {
            "method": "POST",
            "headers": {
                "content-type": "application/json",
                "accept": "application/json"
            },
            "body": JSON.stringify({
                token: props.token,
                questions: questions
            })
        }).then(response => response.json())
            .then(response => {
                console.log(response);
                if(response.error == null){
                    document.getElementById("saveStatus").style.display="block";
                    document.getElementById("saveStatus").innerHTML=text['success-save'];
                }else{
                    document.getElementById("saveStatus").style.display="block";
                    document.getElementById("saveStatus").innerHTML=response.error.message;
                }
            }).catch(err => {
                console.log(err);
                document.getElementById("saveStatus").style.display="none";
            });
    }

    function onValueChange(question, event) {
        var newQuestion = [];
        for (var a = 0; a < questions.length; a++) {
            if (questions[a].id === question.id)
                questions[a].answer = event.target.value;
            newQuestion.push(questions[a]);
        }
        setQuestions(newQuestion);
    }

    return (
        <div className="content">
            <p>{text["please-answer"]}</p>
            <div className="questions-list">
                {questions.map(question => (

                    <span key={question.id}>
                        {question.title}
                        <div className="wrapper">
                            {question.options.map((option, index) => (
                                <span key={index}>
                                    <input type="radio" name="selected" id={"option-"+index} value={option.key} checked={question.answer === option.key}
                                            onChange={event => onValueChange(question, event)} />
                                    <label htmlFor={"option-"+index} className={`option ${question.answer === option.key?'option-'+index:''}`}>
                                        <div className="dot"></div>
                                        <span>{option.value}</span>
                                    </label>
                                </span>
                            ))}
                        </div>
                    </span>
                ))}
            </div>
            <Button name="Kayıt Et" onClick={() => setAnswer()} />
            <p id="saveStatus" style={{display:"none"}}>{text['success-save']}</p>
        </div>)
}
export default Survey;