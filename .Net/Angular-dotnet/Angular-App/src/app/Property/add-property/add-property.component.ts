import { NgForm } from '@angular/forms'
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import {IPropertyBase } from '../../model/IPropertyBase.interface';
@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent implements OnInit {
  @ViewChild('Form') addPropertyForm: NgForm;
  @ViewChild('formTabs') formTabs?: TabsetComponent;

  propertyTypes:Array<String> = ['House','Apartment','Duplex'];
  furnishedType:Array<String> = ['Fully','Semi','Unfurnished'];

  propertyView:IPropertyBase = {
    id:0,
    Name:'',
    Price:'',
    sellRent:1,
    PType:'',
    BHK:0,
    builtArea:0,
    city:'',
    FType:'',
    RTM:0,
    readyToMove:true
  };

  constructor(private route: Router) { }

  ngOnInit() {

    // setTimeout( ()=>{
    //   this.addPropertyForm.controls['Name'].setValue("Name Here");
    // },1000);

  }
  onBack(){
    this.route.navigate(['/']);
  }

  onSubmit(Form?: NgForm){
  //   console.log(Form); // This will be used when form has onSubmit(Form);
    console.log(this.addPropertyForm);
  }

  selectTab(tabId: number) {
    if (this.formTabs?.tabs[tabId]) {
      this.formTabs.tabs[tabId].active = true;
    }
  }
}
