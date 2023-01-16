import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class HousingService {

constructor(private http: HttpClient) { }

getAllListings() {
  return this.http.get('data/list.json').pipe(
    map(data=> {
      const listArray: Array<any> = [];
      for(const id in data){
        if(data.hasOwnProperty(id)){
          listArray.push(data[id]);
        }
      }
      return listArray;
    })
  );

}

}
