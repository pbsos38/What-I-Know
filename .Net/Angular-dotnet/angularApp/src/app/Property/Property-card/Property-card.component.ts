import { Component } from '@angular/core'
@Component({
  selector: 'app-property-card',
  // template: `<h1>This is a card</h1>`,
  templateUrl: 'Property-card.Component.html',
  styleUrls: ['Property-card.Component.css']
})

export class propertyCardCompnent{
  Property: any = {
    "Id":1,
    "Name":"Dummy House 1",
    "Type": "House",
    "Price":12000

  }
}
