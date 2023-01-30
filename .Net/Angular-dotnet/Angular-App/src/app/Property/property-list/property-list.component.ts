import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Property } from 'src/app/model/property';
import { HousingService } from 'src/app/Service/housing.service';
import { IPropertyBase } from '../../model/IPropertyBase.interface';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})

export class PropertyListComponent implements OnInit {
  sellRent= 1;
  list: Array<Property> = [];
  Today = new Date();
  City='';
  searchCity = '';
  SortbyParam = '';
  SortDirection='Asc';
  constructor(private route: ActivatedRoute,private housingService:HousingService) { }

  ngOnInit(): void {
    if(this.route.snapshot.url.toString()==='rent-property'){
      this.sellRent = 2;
    }

    this.housingService.getAllListings(this.sellRent).subscribe(
      data=>{
        this.list = data;
        // const newProperty = JSON.parse(localStorage.getItem('newProp') || '{}');
        // if(newProperty.sellRent===this.sellRent){
        //   this.list = [newProperty,...this.list];
        // }

      }, error =>{
        console.error(error);
      }
    );
  }
  onCityFilter(){
    this.searchCity = this.City;
  }
  onCityFilterClear(){
    this.searchCity='';
    this.City='';
  }
  onSortDirection(){
    if (this.SortDirection === 'Desc') {
      this.SortDirection = 'Asc';
  } else {
      this.SortDirection = 'Desc';
  }
  }
}
