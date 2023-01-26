export interface IPropertyBase {
  id: number;
  sellRent:number;
  Name:string;
  FType:string;
  PType:string;
  BHK:number;
  builtArea:number;
  city:string;
  RTM:number;
  Price:string;
  Image?:string;
  readyToMove: boolean;
  estPossessionOn?: string;
}
