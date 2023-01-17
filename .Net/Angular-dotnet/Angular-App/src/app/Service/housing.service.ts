import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { IProperty } from '../Property/property-list/IProperty.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class HousingService {

constructor(private http: HttpClient) { }

getAllListings(sellRent:number):Observable<IProperty[]> {
  return this.http.get('data/list.json').pipe(
    map(data=> {
      const listArray: Array<IProperty> = [];
      for(const id in data){
        if(data.hasOwnProperty(id) && data[id].sellRent === sellRent){
          listArray.push(data[id]);
        }
      }
      return listArray;
    })
  );

}

}
