import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { IPropertyBase } from '../model/IPropertyBase.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class HousingService {

constructor(private http: HttpClient) { }

getAllListings(sellRent:number):Observable<IPropertyBase[]> {
  return this.http.get('data/list.json').pipe(
    map(data=> {
      const listArray: Array<IPropertyBase> = [];
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
