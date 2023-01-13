const express = require('express');
const mongoose = require('mongoose');

const router = express.Router();
const CourseModel = mongoose.model("course");

router.get("/add",(req,res)=>{
    res.render("add-course");
}); 

router.post("/add",(req,res)=>{
    
    // adding add to table/ collection
    var course = new CourseModel();
    course.courseName = req.body.courseName;
    course.courseDuration =req.body.courseDuration;
    course.courseFee = req.body.courseFee;
    course.courseId = Math.ceil(Math.random()*1000000)+"";
    course.save((err,doc)=>{
        if(!err){
            //res.json({message:"succcessful", status: "OK"})
            res.redirect("/course/list")
        }
        else{
            res.send("Error")
        }
    });
    });

router.get("/list", (req, res) =>{

    // adding add to table/ collection
    // var course = new CourseModel();
    // course.courseName = "Prince";
    // course.courseId ="1";
    // course.save();

    //getting data
    CourseModel.find((err,docs)=>{
        if(!err){
            console.log(docs)
            res.render("list",{data : docs})
        }
        else{
            res.send("Course Error")
        }
    }).lean();
});

module.exports = router;



// for this warning .lean() was added after find query
// Handlebars: Access has been denied to resolve the property "courseId" because it is not an "own property" of its parent.
// You can add a runtime option to disable the check or this warning:
// See https://handlebarsjs.com/api-reference/runtime-options.html#options-to-control-prototype-access for details
// Handlebars: Access has been denied to resolve the property "courseName" because it is not an "own property" of its parent.
// You can add a runtime option to disable the check or this warning:
// See https://handlebarsjs.com/api-reference/runtime-options.html#options-to-control-prototype-access for details