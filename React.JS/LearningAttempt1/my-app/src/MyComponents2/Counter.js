import React, { Component } from 'react'

class Counter extends Component {
    constructor(props) {
        super(props)
        this.state = {
            count: 0
        }
    }
    increment() {
        // this.setState({
        //     count: this.state.count + 1
        // }, () => { console.log("callback value", this.state.count) })
        console.log(this.state.count)// now the count value in console will be less than 1 of actual incremented value because this is async call set state is 
        // is updated after others where as callback value part will be executed after set state

        // to overcome this async thing we will use prevstate instead of current object
        this.setState(prevState => ({
            count:prevState.count+1
        }))
    }
    incrementFive(){
        this.increment()
        this.increment()
        this.increment()
        this.increment()
        this.increment()
        // here this will not increase value by 5 but by 1 only because react collapse repetitive code for better performance
    }
    render() {
        return (
            <div>
                Count: {this.state.count}
                <button onClick={() => this.incrementFive()}>Increment </button>
            </div>
        )
    }
}

export default Counter
