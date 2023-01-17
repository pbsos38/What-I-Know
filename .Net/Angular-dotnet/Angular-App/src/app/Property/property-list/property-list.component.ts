import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HousingService } from 'src/app/Service/housing.service';
import { IProperty } from './IProperty.interface';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})

export class PropertyListComponent implements OnInit {
  sellRent= 1;
  list: Array<IProperty> = [];
  constructor(private route: ActivatedRoute,private housingService:HousingService) { }

  ngOnInit(): void {
    if(this.route.snapshot.url.toString()==='rent-property'){
      this.sellRent = 2;
    }

    this.housingService.getAllListings(this.sellRent).subscribe(
      data=>{
        this.list = data;
      }, error =>{
        console.error(error);
      }
    );
  }

}
