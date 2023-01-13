//fs is inbuilt library to read soft copies of files.
var fs =require('fs');
var data = fs.readFileSync('text.txt');
console.log(data.toString());
console.log('Ends');