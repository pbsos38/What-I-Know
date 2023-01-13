import React from 'react'
import { TodoItem } from "../MyComponents/TodoItem";

export const Todos = (props) => {
    let myStyle = {
        minHeight:"70vh",
        margin: "40px auto"
    }
    return (
        <div className="Container " style={myStyle}>
            <h3 className="my-3">Todos List</h3>
            {props.data.length === 0 ? "No data to Display!!" : 
                props.data.map((todo) => {
                return (
                <div>
                    <TodoItem Todo={todo} key={todo.sno} onDelete={props.onDelete}></TodoItem><hr/>
                </div>
                );
            })}
        </div>
    )
}