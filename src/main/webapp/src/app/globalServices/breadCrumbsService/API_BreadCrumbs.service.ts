import {Injectable} from "angular2/core";
import {Http,URLSearchParams} from "angular2/http";
import "rxjs/add/operator/map";


@Injectable()
export class API_BreadCrumbs {
  constructor(private http:Http) {
  };

  createFullPath(routeParamsId:string) {
      return this.getPathToCategory(routeParamsId);
  }

  getPathToCategory(routeParamsId:string) {
    let params:URLSearchParams = new URLSearchParams();
    params.set('categoryId', routeParamsId.toString());

    return this.http.get('/api/category/getFullPath',{
      search:params
    }).map(res => res.json());
  }
}
