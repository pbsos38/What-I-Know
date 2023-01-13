var express = require('express');
var routes = require('./routes');
var http = require('http');
var path = require('path');
var urlEncoded = require('url');
var  bodyParser = require('body-parser');
var  json = require('json');
var  logger = require('logger');
var  methodOverride= require('method-override');

//var nano = require('nano')('http://localhost:5984/');
var nano = require('nano')('http://admin:admin@127.0.0.1:5984');

var db = nano.use('address');
var app = express();
var server =http.createServer(app);
app.set('port', process.env.PORT || 3000);
app.set('views',path.join(__dirname,'views'));
app.set('view engine','jade');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(methodOverride());
app.use(express.static(path.join(__dirname,'public')));

app.get('/',routes.index);
// or
// app.get('/', function(req, res){
//     res.send('Express works!!');
// })
app.post('/createdb',function(req, res){

    ///#@#@ this is sample to recieve data from client request but has not be used in this project
    let people = {people : [{ name: "Prince"}] }
    if(req.body && req.body.name){
        /// push will append data to array
        people.people.push({name: req.body.name})
    }
    ///#@#@ ended here

    nano.db.create(req.body.dbname, function(err){
        if(err){
            console.log(err);
            res.send("Error creating Database"+ req.body.dbname);
            return;
        }

        res.send("Database " +req.body.dbname +"created.");
    });
});

app.post('/new_contact', function(req, res){
    var name= req.body.name;
    var phone = req.body.phone;
    db.insert({name:name, phone:phone, crazy:true}, phone, function(err,body, header){
        if(err){
            res.send("Error creating contact");
            return;
        }
        res.send("Contact created successfull");
    });
});


app.post('/view_contact', function(req, res){
   var alldoc = "Following are the contacts";
   db.get(req.body.phone,{revs_info:true}, function(err,body){
       if(!err){
           console.log(body);
       }

       if(body){
        alldoc += "Name: "+ body.name+" <br/>Phone Number: "+ body.phone;
    }
       else{
           alldoc = "No records Found";
        }
       res.send(alldoc);
   });
});

app.post('/delete_contact',function(req,res){

    db.get(req.body.phone, {revs_info:true}, function(err){
        if(!err){
            db.destroy(req.body.phone, body._rev , function(err, body){
                if(err){
                    res.send("Error deleting contact.");
                }
            });

            res.send("Contacts deleted successfullly.");
        }
        else console.log(err);
    });
});

http.createServer(app).listen(app.get('port'), function(){
    console.log("Express server listening on port " + app.get('port'));
});


    ///#$#$ this is sample to recieve data from client request but has not be used in this project
/// it is apprieciate at declare methods in increasing orders of receving parameters as shown below because if 
/// in first attemp parameters are not accepted then control will passover to next method
app.get("/people/:name", function(req, res){
    res.json({name: req.params.name});
    res.end();
});

app.get("/people/:name/:age", function(req, res){
    res.json({name: req.params.name},{ age: req.params.age});
    res.end();
});

///#$#$ ends here