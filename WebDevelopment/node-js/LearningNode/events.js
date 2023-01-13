// Node.js follows event driven architectutre 
// Certain Objects( emmiters) periodically emit events which further invokes the listners
// node.js provide concurrency by using the conceptof events and callbacks
// all objects that emit events are instanece of the EventEmitters class.
// example

//// basically node works on events but this case will help us to build our onwn evnets using something like this
var fs = require('fs');
var event = require('events'); // this will import event module 

const myEmitter = new event.EventEmitter(); // here we are creating an object of eventEmitter

fs.readFile('text.txt',(err,data)=>{
    console.log(data.toString());
    myEmitter.emit('ReadFile');// Emitting Event
    // or if we need to pass anyarguments
    //myEmitter.emit('ReadFile','args');// Emitting Event
});

// Registering listner and defining event handler
//// Bascially this will tell us that if event named has occured or not

myEmitter.on('ReadFile', ()=>{
    console.log('\nRead Event Occured!');
    });
// if we are passing any argument in emmitter
    // myEmitter.on('ReadFile', (vari)=>{
//     console.log('\nRead Event Occured!'+vari);
//     });