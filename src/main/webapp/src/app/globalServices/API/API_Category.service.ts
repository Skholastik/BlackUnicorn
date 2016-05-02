import {Injectable} from "angular2/core";
import {Http, Headers,URLSearchParams} from "angular2/http";
import "rxjs/add/operator/map";

import {Category} from '../DTO/category.class.ts';
import {CharacteristicAttribute} from "../DTO/characteristicAttribute.class.ts";

@Injectable()
export class API_Category {
  constructor(private http:Http) {
  };

  getJsonHeader():Headers {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers
  }

  getCategoryListForCreationCategory() {
    return this.http.get('/api/category/getCategoryListForCreationCategory').map(res => res.json());
  }

  getCategoryListForCreationModelCategoryProduct() {
    return this.http.get('/api/category/getCategoryListForCreationModelCategoryProduct').map(res => res.json());
  }

  getCategory(parentCategoryId:string) {

    let params:URLSearchParams = new URLSearchParams();
    params.set('parentId', parentCategoryId.toString());

    return this.http.get('/api/category/getCategoryListByParentId', {
      search: params
    }).map(res => res.json());
  }

  checkCategoryExistence(parentId:string,categoryName:string){
    let params:URLSearchParams = new URLSearchParams();
    params.set('parentId', parentId.toString());
    params.set('categoryName', categoryName.toString());

    return this.http.get('/api/category/checkCategoryExistence', {
       search: params
    }).map(res => res.text());
  }

  addCategoryList(parentId:string, categoryList:Category[]) {
    let transferData:any = JSON.stringify({categoryListDTO:categoryList});

    let headers:Headers = this.getJsonHeader();
    let params:URLSearchParams = new URLSearchParams();
    params.set('parentId', parentId.toString());

    console.log(transferData);
    console.log(parentId);
    return this.http.post('/api/category/addCategoryList', transferData, {
      headers: headers, search: params
    }).map(res => res.text());
  }


  addCategoryAttributes(attributeList:CharacteristicAttribute[], categoryId:string) {

    let transferData:any = JSON.stringify({attributeList:attributeList});
    let headers:Headers = this.getJsonHeader();

    let params:URLSearchParams = new URLSearchParams();
    params.set('categoryId', categoryId.toString());
    console.log('-----------------');
    console.log(transferData);

    return this.http.post('/api/category/addCategoryAttributes',transferData, {
      headers: headers,search: params
    }).map(res => res.json());
  }


}
