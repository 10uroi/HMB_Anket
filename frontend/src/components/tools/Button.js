export default function Button(props) {
    return (<button onClick={props.onClick} className="survey-save-btn">{props.name}</button>);
}