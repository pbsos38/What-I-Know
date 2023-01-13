import "./App.css";
import React, { useState, useEffect } from "react";
import Header from "./MyComponents/Header";
import { AddTodo } from "./MyComponents/AddTodo";
import { Todos } from "./MyComponents/Todos";
import ClassBasedComponenets from "./MyComponents2/ClassBasedStates";
import Counter from "./MyComponents2/Counter";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
// https://reactrouter.com/web/guides/quick-start
/*
Note there is a small difference in the importing modules style 
import Header from "./MyComponents/Header";
import {Todos} from "./MyComponents/Todos";
importing without {} means we are including a module with default properties 
like 
export default function header() {
    return ();
}

Whereas importing with {} means including modules except default ones like
export const Todos = () => {
    return (
        <div></div>
    );
}
*/
import { Footer } from "./MyComponents/Footer";
function App() {
  let initTodo;
  if (localStorage.getItem("todoList") === null) {
    initTodo = [];
  } else {
    initTodo = JSON.parse(localStorage.getItem("todoList"));
  }
  const onDelete = (para1) => {
    console.log(para1);
    // let index = todoList.indexOf(para1);
    // todoList.splice(index,1);
    // now remove an object from list upper way doesn't work
    // To overcome this and bring realtime effect we have to use useState
    setCount(
      todoList.filter((e) => {
        return e !== para1;
      })
    );
    localStorage.setItem("todoList", JSON.stringify(todoList));
  };
  const [todoList, setCount] = useState(initTodo);

  const addTodo = (title, desc) => {
    let sno;
    if (todoList.length === 0) {
      sno = 0;
    } else {
      sno = todoList[todoList.length - 1].sno + 1;
    }

    const newTodo = {
      sno: sno,
      title: title,
      desc: desc,
    };
    setCount([...todoList, newTodo]);
    useEffect(() => {
      localStorage.setItem("todoList", JSON.stringify(todoList));
    }, [todoList]);
  };
  return (
    <div>
      <Router>
        <Header title="My Todos List " SearchBar={true}>
          {/* Try commenting/uncommenting the bottom line to see difference for the props.children method used in header.js */}
          {/* <a>Header</a> */}
        </Header>
        <Switch>
          <Route path="/about"></Route>
          <Route
            path="/lesson2"
            render={() => {
              return (
                <div className="container">
                  {" "}
                  <ClassBasedComponenets name="Mr. Developer" />
                  <Counter />
                </div>
              );
            }}
          ></Route>
          <Route
            path="/"
            render={() => {
              return (
                <div className="container">
                  {" "}
                  <AddTodo addTodo={addTodo} />
                  <Todos data={todoList} onDelete={onDelete} />
                </div>
              );
            }}
          ></Route>
        </Switch>
      </Router>
      <Footer />
    </div>
  );
}

export default App;
