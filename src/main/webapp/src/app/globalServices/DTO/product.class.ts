import {Serializable} from "./serializable.class.ts";

import {Characteristic} from '../../globalServices/DTO/characteristic.class';

export class Product extends Serializable {

  id:string;
  description:string;
  characteristicList:Characteristic[];
  imagePathList:string[];

  addCharacteristic(characteristic:Characteristic) {
      if(this.characteristicList==undefined)
        this.characteristicList=[];
      else
        this.characteristicList.push(characteristic);
  }
}
