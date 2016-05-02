import {Injectable} from "angular2/core";
import {Http, Headers,URLSearchParams} from "angular2/http";
import "rxjs/add/operator/map";


@Injectable()
export class API_View {
  constructor(private http:Http) {
  };

  getJsonHeader():Headers {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers
  }

  getViewList() {
    return this.http.get('/api/view/getViewList').map(res => res.json());
  }
}
