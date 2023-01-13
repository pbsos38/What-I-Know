const mongoose = require('mongoose');


//since mongodb is schema less so we have to defien for once
var courseSchema = new mongoose.Schema({

    courseName: {
        type: String,
        required: "required"
    },
    courseId: {
        type : String
    },
    courseDuration: {
        type: String
    },
    courseFee: {
        type: String
    }

 });

 mongoose.model("course",courseSchema);