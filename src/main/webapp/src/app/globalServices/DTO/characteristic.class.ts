import {Serializable} from "./serializable.class.ts";

import {View} from "./view.class";
import {CharacteristicAttribute} from "./characteristicAttribute.class";

export class Characteristic extends Serializable {

  attribute:CharacteristicAttribute;
  value:string;
}
