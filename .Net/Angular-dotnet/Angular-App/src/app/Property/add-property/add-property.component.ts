import { NgForm } from '@angular/forms'
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent implements OnInit {
  @ViewChild('Form') addPropertyForm: NgForm;
  constructor(private route: Router) { }

  ngOnInit() {

    setTimeout( ()=>{
      this.addPropertyForm.controls['Name'].setValue("Name Here");
    },1000);

  }
  onBack(){
    this.route.navigate(['/']);
  }

  onSubmit(Form?: NgForm){
  //   console.log(Form); // This will be used when form has onSubmit(Form);
    console.log(this.addPropertyForm);
  }
}