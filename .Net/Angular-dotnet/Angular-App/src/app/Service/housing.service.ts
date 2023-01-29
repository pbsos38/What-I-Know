import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { IPropertyBase } from '../model/IPropertyBase.interface';
import { Observable } from 'rxjs';
import { Property } from '../model/property';

@Injectable({
  providedIn: 'root'
})

export class HousingService {

  constructor(private http: HttpClient) { }

  getAllListings(sellRent: number): Observable<IPropertyBase[]> {
    return this.http.get('data/list.json').pipe(
      map(data => {
        const listArray: Array<Property> = [];
        const newProperty = JSON.parse(localStorage.getItem('newProp') || '{}');

        if(newProperty){
          for (const id in newProperty) {
            if (newProperty.hasOwnProperty(id) && newProperty[id].sellRent === sellRent) {
              listArray.push(newProperty[id]);
            }
          }
        }
        // if(newProperty.sellRent===this.sellRent){
        //   this.list = [newProperty,...this.list];
        // }

        for (const id in data) {
          if (data.hasOwnProperty(id) && data[id].sellRent === sellRent) {
            listArray.push(data[id]);
            console.log(listArray);
          }
        }
        console.log(listArray);

        return listArray;
      })
    );

  }

  addProperty(property: Property) {
    let newProp: Property[];
    if(localStorage.getItem('newProp')){

      newProp = [property, ...JSON.parse(localStorage.getItem('newProp') as string)];
    }else{
      newProp = [property];
    }
    localStorage.setItem("newProp", JSON.stringify(newProp));
  }

  newPropId(){
    if(localStorage.getItem('PID')){
      const temp= String(+(localStorage.getItem('PID') as string)+1);
      localStorage.setItem('PID',temp);
      return +temp;

    }else{
      localStorage.setItem('PID','101');
      return 101;
    }
  }

}
