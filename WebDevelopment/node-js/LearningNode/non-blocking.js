const { Console } = require('console');
var fs =require('fs');
fs.readFile('text.txt',function(err,data){
    if(err){
        console.log(err);
    }

// as we know async functions can wait for a while or as per our requirements
    setTimeout(()=>{
        console.log(data.toString());
    },2000);
});
console.log("Starting");