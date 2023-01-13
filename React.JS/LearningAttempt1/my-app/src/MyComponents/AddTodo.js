import React,{useState} from 'react'


export const AddTodo = (props) => {
    const [title,setTitle] = useState("");
    const [desc, setDesc] = useState("");

    const submit = (e) =>{
        e.preventDefault();// This will prevent from reloading page.
        if(!title || !desc){
            alert("Error");
        }
        
        props.addTodo(title,desc);
        setTitle("");
        setDesc("");
    }

    return (
        <form onSubmit={submit}>
            <div ClassName=" container mb-3">
                <label htmlFor="title" ClassName="form-label">Todo Title</label>
                <input type="text" value={title} onChange={(e)=>setTitle(e.target.value)} ClassName="form-control" id="title" aria-describedby="emailHelp" />
            </div>
            <div ClassName="mb-3">
                <label htmlFor="desc" ClassName="form-label">Description</label>
                <input type="text" value={desc} onChange={(e)=>setDesc(e.target.value)} ClassName="form-control" id="desc" />
            </div>
            <button type="submit" ClassName="btn btn-primary">Add Todo</button>
        </form>
    );
}
