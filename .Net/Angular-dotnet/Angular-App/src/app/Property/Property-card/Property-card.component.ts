import { Component, Input } from '@angular/core';
import { IPropertyBase } from '../../model/IPropertyBase.interface';


@Component({
  selector: 'app-property-card',
  // template: `<h1>This is a card</h1>`,
  templateUrl: 'Property-card.component.html',
  styleUrls: ['Property-card.component.css']
})

export class propertyCardCompnent{
  @Input() Property :IPropertyBase;
  @Input() hideIcons:boolean;

}
