import {Serializable} from "./serializable.class.ts";
import {View} from "./view.class";

export class CharacteristicAttribute extends Serializable {

  id:string;
  name:string;
  unitOfMeasurement:string;
  viewType:string;

}


