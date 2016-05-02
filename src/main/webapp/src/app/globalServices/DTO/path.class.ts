import {Serializable} from "./serializable.class.ts";

export class Path extends Serializable {
  private uniqueKey:number;
  private id:string;
  private name:string;
  private signature:string;

  constructor(uniqueKey:number,id:string,name:string, signature:string) {
    super();
    this.uniqueKey=uniqueKey;
    this.id=id;
    this.name=name;
    this.signature=signature;
  }

  getUniqueKey():number {
    return this.uniqueKey
  }

  setUniqueKey(uniqueKey:number) {
    this.uniqueKey = uniqueKey;
  }

  getId():string {
    return this.id
  }

  setId(id:string) {
    this.id = id;
  }

  getName():string {
    return this.name
  }

  setName(name:string) {
    this.name = name;
  }

  getSignature():string {
    return this.signature
  }

  setSignature(signature:string) {
    this.signature = signature;
  }

}
