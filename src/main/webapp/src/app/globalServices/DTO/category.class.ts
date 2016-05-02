import {Serializable} from "./serializable.class.ts";

export class Category extends Serializable {

  id:string;
  name:string;
  hasProductCategoryModel:boolean;


  getId():string {
    return this.id;
  }

  setId(value:string) {
    this.id = value;
  }

  getName():string {
    return this.name;
  }

  setName(value:string) {
    this.name = value;
  }

  getHasProductCategoryModel():boolean {
    return this.hasProductCategoryModel;
  }

  setHasProductCategoryModel(value:boolean) {
    this.hasProductCategoryModel = value;
  }
}
