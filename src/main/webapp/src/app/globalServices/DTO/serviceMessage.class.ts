import {Serializable} from "./serializable.class.ts";

export class ServiceMessage extends Serializable {
  result:boolean;
  message:string;

  setInnerMessage(result:boolean,message:string){
    this.result=result;
    this.message=message;
  }

  getResult():boolean {
    return this.result
  }

  getMessage():string {
    return this.message
  }

  setResult(result:boolean){
    this.result=result;
  }

  setMessage(message:string){
    this.message=message;
  }
}
