// to use HTTP server and Client one must require('http')
// the HTTP interfaces in Node.js are designrd to support many features of the protocol

//import reuired modules
var http = require('http');
var fs = require('fs');
var url = require('url');

http.createServer(function( request, response){ // creating server

    var pathName = url.parse(request.url).pathname;  // parse the fetched URL to get pathname

    console.log("Request For "+ pathName + "Recieved.");

    fs.readFile(pathName.substr(1), function(err, data){ // request file to be read from file system(index.html)

        if(err){
            console.log(err);
            response.writeHead(404,{ 'content-Type': 'text/html'}); // creating header with content type as text or html
        }
        else{
            response.writeHead(200,{'content-Type': 'text/html'}); // Generating Response
            response.write(data.toString()); // ""
        }
        response.end();
    });
}).listen(3000); /// Listening to port: 3000
 console.log("Server running at localHost: 3000");