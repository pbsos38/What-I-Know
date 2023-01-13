import React from 'react'
// import { propTypes } from 'react-bootstrap/esm/Image';
import {PropTypes} from "prop-types";
import {
    Link
  } from "react-router-dom";
/*
 * There are 2 ways to recieve data and render data in parameters 
    1) export default function header(props) {
        return (
            <> {props.para1}</>
        );
    }

    2)export default function header({para1,para2}) {
        return (
            <>{para1} + {para2}</>
        );
    }

 * 
 */
export default function header(props) {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <Link className="navbar-brand" to="#">{props.title}{props.children}</Link>{/** this props.children will only work if the parent tag has child element else it will be ignored */}
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="/">Home</Link>
                        </li>

                        <li class="nav-item">
                            <Link className="nav-link" to="/about"  aria-disabled="true">About</Link>
                        </li>
                        <li class="nav-item">
                            <Link className="nav-link" to="/lesson2"  aria-disabled="true">lesson2</Link>
                        </li>
                    </ul>
                    {/* Sample for if else */}
                    {props.searchBar? <form class="d-flex">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form>: ""
                    }                </div>
            </div>
        </nav>
    );
}

// This helps in setting up the default values for the defined variables
header.defaultProps = {
    title:"Null",
    SearchBar: true
}

// This is an optional but recommended things
// Here we are declaring a varible's data type 
header.propTypes = {
    title: PropTypes.string,
    SearchBar: PropTypes.bool.isRequired
}