import {Injectable} from "angular2/core";

@Injectable()
export class TextFormatter {

  constructor() {}

  wordProcessor(word:String): string{
    var firstLevelOfProcessing=word.trim().toLowerCase().replace(/ +/g,' ');
    return firstLevelOfProcessing.substring(0,1).toUpperCase()+firstLevelOfProcessing.substring(1);

  }

}
