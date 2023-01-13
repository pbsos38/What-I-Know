var first_attempt = 'Hello';
console.log(first_attempt);

var age1 = 30;
var age2 = 30;
var result = age1==age2;
console.log(result);//true

var age1 = 30;
var age2 = '30';
var result = age1==age2;
console.log(result); //true


var age1 = 30;
var age2 = '30';
var result = age1===age2;
console.log(result); //false
// conclusion node doesn't care about data types


//              FUNCTIONS
 function sayHello(name , last)
 {
     return 'hello '+name +'!!!' + last ;
 }
  console.log(sayHello('Ravi', "Bansal"));// hello ravi!!!
  //          OR  
//shown beloe are known as anoynonmus function
  var sayHello=function (name, last)
 {
     return 'hello '+name +'!!!'+ last ;
 }
  console.log(sayHello('Ravi',  "Bansal"));//hello ravi!!!

  //            OBJECTS

  var student = {
      name : 'ravi',
      email:'pbsos38@gmail.com'
  };
  console.log(student.email,student.name);




  //            FILE SYSTEMS

  var fs= require('fs');

  fs.readFile('./hello.txt',function(err,data){
      if(!err){
          console.log(data.toString());
      }
  });

  var fs1= require('fs');
fs.writeFile('./ReriddenText.txt','Rewrite saved content of file and it can be of any type like objects varables etc.',function(err){
    if(!err)
    {
        fs1.readFile('./ReriddenText.txt',function(err,data){
            if(!err){
                console.log(data.toString());
            }
    });
  }
});
// some other types are 
// 1)CreateFile
// 2) UpdateFile
// 3)DeleteFile
// 4)RenameFile

  
//              CREATING SERVER

//import events module
// var http = require('http');

// create an evenEmitter object
// var server = http.createServer(function(req,res){
//     res.end('Server works'); 
// });
// server.listen(3000,'localhost',function(){
//     console.log('server started on port :3000');
// });

//conclusion: 1) now if run the current file in cmd then we would be able to notice taht now the command is not 
//going to end automatically instead of that we have to press "ctrl+c"
//2) if we type localhost:3000 in our browser then we can see all the activities performed by us
//and if we stop our server then activity in the browser will also stop


var http = require('http');
var events = require('events');

var eventEmitter = new events.EventEmitter();

var server = http.createServer(function(req,res){
   //Binding event and event handler
    eventEmitter.emit('someone Requested');//event trigger
    res.end('Server works'); 

});

// Fire an event
eventEmitter.on('someone Requested',function(data){
    console.log('A request has been done on the server',data);

});//event lisenter
server.listen(3000,'localhost',function(){
    console.log('server started on port :3000');
});

// conclusion : 
// 1)now we must have noticed that on clicking once on refresh button,
// there are 2 new commands in cmd. This Happens because one is checking whether the 
// method get is available on the server or and thus he other request is executed 
// with the method get
// 2)when the event trigger is triggered then event listener would be initialised automatically
 
// uses: mostly used in chats robo chats

// creating server with EXPRESS jS
var express = require('express');
var server=express();

server.get('/',function(req,res){
res.send('<h1>Express works!</h1>')
});
server.listen(4000,function(){
    console.log('server listening to port 4000')
})

// if there is no coding in file then there would be error in browser saying that cannot get /

//file linking in express and creating server using both express and node

var express = require('express');
var http=require('http');
var fs=require('fs');

var app=express();
var server=http.createServer(app);


app.get('/',function(req,res){
res.send('<h1>Express works!</h1>')
});

app.get('/tasks',function(req,res){
fs.readFile('./package.json',function(err,data){
    var tasks =JSON.parse(data.toString()).tasks;
    res.json(tasks);
});
});
server.listen(5000,function(){
    console.log('server listening to port 5000')
})



