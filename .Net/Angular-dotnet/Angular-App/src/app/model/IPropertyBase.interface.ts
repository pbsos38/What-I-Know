export interface IPropertyBase {
  id: number;
  sellRent:number;
  name:string;
  furnishingType:string;
  propertyType:string;
  bhk:number;
  builtArea:number;
  city:string;
  RTM:number;
  price:string;
  image?:string;
  estPossessionOn?: string;
  readyToMove:boolean;
}
