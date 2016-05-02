import {Injectable} from "angular2/core";
import {Http, Headers,URLSearchParams} from "angular2/http";
import "rxjs/add/operator/map";


@Injectable()
export class API_CharacteristicAttribute {
  constructor(private http:Http) {
  };

  getJsonHeader():Headers {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers
  }

  getCharacteristicAttributeList() {
    return this.http.get('/api/characteristicAttribute/getCharacteristicAttributeList').map(res => res.json());
  }
}
