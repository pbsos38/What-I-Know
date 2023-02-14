import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Property } from '../model/property';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})

export class HousingService {
  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAllListings(sellRent?: number): Observable<Property[]> {
    return this.http.get('data/list.json').pipe(
      map(data => {
        const listArray: Array<Property> = [];
        const newProperty = JSON.parse(localStorage.getItem('newProp') || '{}');

        if (newProperty) {
          for (const id in newProperty) {
            if (sellRent) {
              if (newProperty.hasOwnProperty(id) && newProperty[id].sellRent === sellRent) {
                listArray.push(newProperty[id]);
              }
            } else {
              listArray.push(newProperty[id]);
            }
          }
        }
        // if(newProperty.sellRent===this.sellRent){
        //   this.list = [newProperty,...this.list];
        // }

        for (const id in data) {
          if (sellRent) {
            if (data.hasOwnProperty(id) && data[id].sellRent === sellRent) {
              listArray.push(data[id]);
              console.log(listArray);
            }
          } else {
            listArray.push(data[id]);

          }
        }
        console.log(listArray);

        return listArray;
      })
    );

  }


  getProperty(id: number) {
    return this.getAllListings().pipe(
      map(propertyArray => {
        // throw new Error("some error");
        return propertyArray.find(p => p.id === id) as Property;
      })
    );
  }

  addProperty(property: Property) {
    let newProp: Property[];
    if (localStorage.getItem('newProp')) {

      newProp = [property, ...JSON.parse(localStorage.getItem('newProp') as string)];
    } else {
      newProp = [property];
    }
    localStorage.setItem("newProp", JSON.stringify(newProp));
  }

  newPropId() {
    if (localStorage.getItem('PID')) {
      const temp = String(+(localStorage.getItem('PID') as string) + 1);
      localStorage.setItem('PID', temp);
      return +temp;

    } else {
      localStorage.setItem('PID', '101');
      return 101;
    }
  }

  getAllCities(): Observable<string[]>{
    return this.http.get<string[]>(this.baseUrl+'/api/City/cities');
  }

}
