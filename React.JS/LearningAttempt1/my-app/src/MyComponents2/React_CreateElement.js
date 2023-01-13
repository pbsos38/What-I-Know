import React from 'react'

export default function React_CreateElement() {
    return (
        React.createElement('div',{id:'ele1',className:'mb-6'},React.createElement('h4',null,"Whats up!"))
    )
}
