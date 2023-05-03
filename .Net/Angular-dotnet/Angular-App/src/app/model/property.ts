import { IPropertyBase } from './IPropertyBase.interface';

export class Property implements IPropertyBase {
  id: number;
  sellRent: number;
  name: string;
  propertyType: string;
  bhk: number;
  furnishingType: string;
  price: string;
  builtArea: number;
  carpetArea?: number;
  address: string;
  address2?: string;
  city: string;
  floorNo?: string;
  totalFloors?: string;
  RTM: number;
  AOP?: string;
  mainEntrance?: string;
  security?: number;
  gated?: number;
  maintenance?: number;
  estPossessionOn?: string;
  image?: string;
  description?: string;
  PostedOn?: string;
  PostedBy?: number;
  readyToMove:boolean;
}