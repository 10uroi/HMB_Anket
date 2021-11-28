export default function CircularProgressBar(props) {

    const sqSize = props.sqSize;
    const radius = (props.sqSize - props.strokeWidth) / 2;
    const viewBox = `0 0 ${sqSize} ${sqSize}`;
    const dashArray = radius * Math.PI * 2;
    const dashOffset = dashArray - dashArray * props.percentage / 100;

    return (
        <div style={{marginTop:"20px"}}>
            <svg
                width={props.sqSize}
                height={props.sqSize}
                viewBox={viewBox}>
                <circle
                    className="circle-background"
                    cx={props.sqSize / 2}
                    cy={props.sqSize / 2}
                    r={radius}
                    strokeWidth={`${props.strokeWidth}px`} />
                <circle
                    className="circle-progress"
                    cx={props.sqSize / 2}
                    cy={props.sqSize / 2}
                    r={radius}
                    strokeWidth={`${props.strokeWidth}px`}
                    transform={`rotate(-90 ${props.sqSize / 2} ${props.sqSize / 2})`}
                    style={{
                        strokeDasharray: dashArray,
                        strokeDashoffset: dashOffset
                    }} />
                <text
                    className="circle-text"
                    x="50%"
                    y="50%"
                    dy=".3em"
                    textAnchor="middle">
                    {`${props.percentage}%`}
                </text>
            </svg>
        </div>
    );
}