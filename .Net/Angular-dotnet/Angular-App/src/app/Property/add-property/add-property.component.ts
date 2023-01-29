import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms'
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { IPropertyBase } from '../../model/IPropertyBase.interface';
import { AlertyfyService } from 'src/app/Service/alertyfy.service';
import { Property } from 'src/app/model/property';
import { HousingService } from 'src/app/Service/housing.service';
@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent implements OnInit {
  // @ViewChild('Form') addPropertyForm: NgForm;
  @ViewChild('formTabs') formTabs?: TabsetComponent;

  addPropertyForm: FormGroup;
  propertyTypes: Array<String> = ['House', 'Apartment', 'Duplex'];
  furnishedType: Array<String> = ['Fully', 'Semi', 'Unfurnished'];
  NextClicked: boolean;
  property= new Property();

  propertyView: IPropertyBase = {
    id: 0,
    Name: '',
    Price: '',
    sellRent: 1,
    PType: '',
    BHK: 0,
    builtArea: 0,
    city: '',
    FType: '',
    RTM: 0,
    readyToMove: true
  };

  constructor(private fb: FormBuilder, private route: Router,
    private housingService:HousingService, private alertify:AlertyfyService) {

   }

  ngOnInit() {

    // setTimeout( ()=>{
    //   this.addPropertyForm.controls['Name'].setValue("Name Here");
    // },1000);

    this.createAddPropertyForm();
  }

  createAddPropertyForm() {
    this.addPropertyForm = this.fb.group({
      BasicInfo: this.fb.group({
        SellRent: ['1', Validators.required],
        BHK: [null, Validators.required],
        Name: [null, Validators.required],
        PType: [null, Validators.required],
        city: [null, Validators.required],
        FType: [null, Validators.required],
      }),
      PriceInfo: this.fb.group({
        Price: [null, Validators.required],
        builtArea: [null, Validators.required],
        CarpetArea: [null],
        Security: [null],
        Maintenance: [null]
      }),
      AddressInfo: this.fb.group({
        FloorNo: [null],
        TotalFloor: [null],
        Address: [null, Validators.required],
        LandMark: [null],
      }),

      OtherInfo: this.fb.group({
        RTM: [null, Validators.required],
        PossessionOn: [null],
        AOP: [null],
        Gated: [null],
        MainEntrance: [null],
        Description: [null]
      })
    })
  }

  onBack() {
    this.route.navigate(['/']);
  }

  onSubmit(Form?: NgForm) {
    this.NextClicked = true;
    if (this.allTabsValid()) {
      this.mapProperty();
      this.housingService.addProperty(this.property);
      this.alertify.success('Congrats, your property listed successfully on our website');
      console.log(this.addPropertyForm);

      if(this.SellRent.value === '2') {
        this.route.navigate(['/rent-property']);
      } else {
        this.route.navigate(['/']);
      }


    } else {
      this.alertify.error('Please review the form and provide all valid entries');
    }

    //   console.log(Form); // This will be used when form has onSubmit(Form);
    console.log(this.addPropertyForm);
  }

  selectTab(tabId: number, IsCurrentTabValid?: boolean) {
    this.NextClicked = true;
    if (this.formTabs?.tabs[tabId] && IsCurrentTabValid) {
      this.formTabs.tabs[tabId].active = true;
    }
  }
  allTabsValid(): boolean {
    if (this.formTabs?.tabs[0] && this.BasicInfo.invalid) {
      this.formTabs.tabs[0].active = true;
      return false;
    }

    if (this.formTabs?.tabs[1] && this.PriceInfo.invalid) {
      this.formTabs.tabs[1].active = true;
      return false;
    }

    if (this.formTabs?.tabs[2] && this.AddressInfo.invalid) {
      this.formTabs.tabs[2].active = true;
      return false;
    }

    if (this.formTabs?.tabs[3] && this.OtherInfo.invalid) {
      this.formTabs.tabs[3].active = true;
      return false;
    }
    return true;
  }

  mapProperty(): void {
    this.property.id = this.housingService.newPropId();
    this.property.sellRent = +this.SellRent.value; // + sign to parse into numberB
    this.property.BHK = this.BHK.value;
    this.property.PType = this.PType.value;
    this.property.Name = this.Name.value;
    this.property.city = this.City.value;
    this.property.FType = this.FType.value;
    this.property.Price = this.Price.value;
    this.property.Security = this.Security.value;
    this.property.Maintenance = this.Maintenance.value;
    this.property.builtArea = this.BuiltArea.value;
    this.property.CarpetArea = this.CarpetArea.value;
    this.property.FloorNo = this.FloorNo.value;
    this.property.TotalFloor = this.TotalFloor.value;
    this.property.Address = this.Address.value;
    this.property.Address2 = this.LandMark.value;
    this.property.RTM = this.RTM.value;
    this.property.AOP = this.AOP.value;
    this.property.Gated = this.Gated.value;
    this.property.MainEntrance = this.MainEntrance.value;
    this.property.Possession = this.PossessionOn.value;
    this.property.Description = this.Description.value;
    this.property.PostedOn = new Date().toString();
  }

//#region <Getter Methods>
  // #region <FormGroups>
  get BasicInfo() {
    return this.addPropertyForm.controls['BasicInfo'] as FormGroup;
  }

  get PriceInfo() {
    return this.addPropertyForm.controls['PriceInfo'] as FormGroup;
  }

  get AddressInfo() {
    return this.addPropertyForm.controls['AddressInfo'] as FormGroup;
  }

  get OtherInfo() {
    return this.addPropertyForm.controls['OtherInfo'] as FormGroup;
  }
// #endregion

//#region <Form Controls>
  get SellRent() {
    return this.BasicInfo.controls['SellRent'] as FormControl;
  }

  get BHK() {
    return this.BasicInfo.controls['BHK'] as FormControl;
  }

  get PType() {
    return this.BasicInfo.controls['PType'] as FormControl;
  }

  get FType() {
    return this.BasicInfo.controls['FType'] as FormControl;
  }

  get Name() {
    return this.BasicInfo.controls['Name'] as FormControl;
  }

  get City() {
    return this.BasicInfo.controls['city'] as FormControl;
  }

  get Price() {
    return this.PriceInfo.controls['Price'] as FormControl;
  }

  get BuiltArea() {
    return this.PriceInfo.controls['builtArea'] as FormControl;
  }

  get CarpetArea() {
    return this.PriceInfo.controls['CarpetArea'] as FormControl;
  }

  get Security() {
    return this.PriceInfo.controls['Security'] as FormControl;
  }

  get Maintenance() {
    return this.PriceInfo.controls['Maintenance'] as FormControl;
  }

  get FloorNo() {
    return this.AddressInfo.controls['FloorNo'] as FormControl;
  }

  get TotalFloor() {
    return this.AddressInfo.controls['TotalFloor'] as FormControl;
  }

  get Address() {
    return this.AddressInfo.controls['Address'] as FormControl;
  }

  get LandMark() {
    return this.AddressInfo.controls['LandMark'] as FormControl;
  }

  get RTM() {
    return this.OtherInfo.controls['RTM'] as FormControl;
  }

  get PossessionOn() {
    return this.OtherInfo.controls['PossessionOn'] as FormControl;
  }

  get AOP() {
    return this.OtherInfo.controls['AOP'] as FormControl;
  }

  get Gated() {
    return this.OtherInfo.controls['Gated'] as FormControl;
  }

  get MainEntrance() {
    return this.OtherInfo.controls['MainEntrance'] as FormControl;
  }

  get Description() {
    return this.OtherInfo.controls['Description'] as FormControl;
  }

//#endregion
//#endregion
}
