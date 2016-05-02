import {Injectable} from "angular2/core";
import {Http, Headers,URLSearchParams} from "angular2/http";
import "rxjs/add/operator/map";

@Injectable()
export class API_ProductCategoryModel {
  constructor(private http:Http) {
  };

  getJsonHeader():Headers {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers
  }

  getProductCategoryModels(productCategoryId:string, maxResult:number, currentPage:number, needToReceiveAttributes:boolean) {

    let params:URLSearchParams = new URLSearchParams();
    params.set('productCategoryId', productCategoryId.toString());
    params.set('maxResult', maxResult.toString());
    params.set('currentPage', currentPage.toString());
    params.set('needToReceiveAttributes', needToReceiveAttributes.toString());

    return this.http.get('/api/productCategoryModel/getProductCategoryModelList', {
      search: params
    }).map(res => res.json());
  }
}
