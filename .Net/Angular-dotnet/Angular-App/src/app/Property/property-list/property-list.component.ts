import { Component, OnInit } from '@angular/core';
import { HousingService } from 'src/app/Service/housing.service';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})

export class PropertyListComponent implements OnInit {

  list: Array<any> = [];
  constructor(private housingService:HousingService) { }

  ngOnInit(): void {
    this.housingService.getAllListings().subscribe(
      data=>{
        this.list = data;
      }, error =>{
        console.error(error);
      }
    );
  }

}
