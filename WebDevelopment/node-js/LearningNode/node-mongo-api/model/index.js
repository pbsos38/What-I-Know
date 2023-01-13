const mongoose = require("mongoose");

/// make sure that mongodb server is running before running this file
mongoose.connect("mongodb://localhost:27017/testing",{ useUnifiedTopology: true ,useNewUrlParser: true }, (Rrror) => {

        if(!Rrror){
            console.log("Success");
        }
        else
        {
            console.log("errror:"+ Rrror);
        }

    });

const course = require("./course.model");
