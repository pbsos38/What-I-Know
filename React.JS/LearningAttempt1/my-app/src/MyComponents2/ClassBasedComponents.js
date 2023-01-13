import React, { Component } from 'react'

export default class ClassBasedComponents extends Component {
    render() {
        return (
            <div>
                    <h5>This is how class based Component looks like {this.props.name}!!!</h5>
            </div>
        )
    }
}
