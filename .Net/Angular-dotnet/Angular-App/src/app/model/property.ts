import { IPropertyBase } from './IPropertyBase.interface';

export class Property implements IPropertyBase {
  readyToMove: boolean;
  id: number;
  sellRent: number;
  Name: string;
  PType: string;
  BHK: number;
  FType: string;
  Price: string;
  builtArea: number;
  CarpetArea?: number;
  Address: string;
  Address2?: string;
  city: string;
  FloorNo?: string;
  TotalFloor?: string;
  RTM: number;
  AOP?: string;
  MainEntrance?: string;
  Security?: number;
  Gated?: number;
  Maintenance?: number;
  Possession?: string;
  Image?: string;
  Description?: string;
  PostedOn: string;
  PostedBy: number;
}