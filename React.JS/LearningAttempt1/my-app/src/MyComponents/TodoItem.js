import React from 'react'

export const TodoItem = ({Todo,onDelete}) => {
    return (
        <div>
            <h5>{Todo.title}</h5>
            <p>{Todo.desc} </p>
            <button className="btn  btn-sm btn-danger" onClick={()=>{onDelete(Todo)}}>Delete</button>
        </div>
    )
}
