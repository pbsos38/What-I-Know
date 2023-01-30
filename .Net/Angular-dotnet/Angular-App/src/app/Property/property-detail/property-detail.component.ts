import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Property } from 'src/app/model/property';
import { HousingService } from 'src/app/Service/housing.service';

@Component({
  selector: 'app-property-detail',
  templateUrl: './property-detail.component.html',
  styleUrls: ['./property-detail.component.css']
})
export class PropertyDetailComponent implements OnInit {
  public propertyId: number;
  property = new Property();
  constructor(private route:ActivatedRoute,private router:Router,private housingService: HousingService) { }

  ngOnInit() {
    // this.propertyId = Number(this.route.snapshot.params['id']);
    this.propertyId = +(this.route.snapshot.params['id']);

    this.route.data.subscribe(
      (data:any) =>{
        this.property = data['prp']; // prp is the name of resolve which can be found in app.module
      }
    )

// Below Method is used when fetching the data without the route resolver
    // this.route.params.subscribe(
    //   (params) =>{
    //     this.propertyId = +params['id'];
    //     this.housingService.getProperty(this.propertyId).subscribe(
    //       (data:Property) => {
    //         this.property= data;
    //       },error => this.router.navigate(['/'])
    //     )
    //   }
    // )
  }

  // onSelectNext(){
  //   this.propertyId +=1;

  //   this.router.navigate(['property-details',this.propertyId]);
  // }
}
