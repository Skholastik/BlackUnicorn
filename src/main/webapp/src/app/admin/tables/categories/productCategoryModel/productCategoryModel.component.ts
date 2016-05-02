import {Component} from 'angular2/core';
import {Router,RouteParams} from 'angular2/router';
import {NgClass} from 'angular2/common';

import {BreadCrumbsService} from '../../../.././globalServices/breadCrumbsService/breadCrumbs.service';
import {API_ProductCategoryModel} from '../../../../globalServices/API/API_ProductCategoryModel.service';
import {Product} from '../../../.././globalServices/DTO/product.class';
import {Characteristic} from '../../../../globalServices/DTO/characteristic.class';
import {CharacteristicAttribute} from '../../../../globalServices/DTO/characteristicAttribute.class';


@Component({
  selector: 'product-category',
  pipes: [],
  providers: [API_ProductCategoryModel],
  directives: [NgClass],
  styleUrls: ['../../../../assets/css/admin/productCategoryModel.css'],
  template: require('./productCategoryModel.html')
})

export class ProductCategoryModel {

  constructor(private router:Router,
              private routeParams:RouteParams,
              private api_ProductCategoryModel:API_ProductCategoryModel,
              private breadCrumbsService:BreadCrumbsService) {
  }

  private productCategoryId:string;
  private characteristicAttributeList:CharacteristicAttribute[];
  private products:Product[];
  private numberOfPages:number[] = [1];
  private currentPage:number = 1;
  private needToReceiveAttributes:boolean = true;
  private maxResult:number = 10;

  /** Необходимо загрузить таблицу сущностей по ID вышестоящей сущности, в зависимости от того был ли последовательный переход или
   переход осуществлялся по ссылке, как происходит и при перезагрузке страницы, будет принято решение откуда брать ID.
   Т.е если переход последовательный, то ID вышестоящей сущности хранится в навигационной цепочке, если же переход 'ссылочный', то
   необходимо создать навигационную цепочку и оттуда вернуть ID вышестоящей сущности */


  ngOnInit() {
    /** Если последовательный переход */
    this.productCategoryId = this.routeParams.get('categoryId');

    if (this.breadCrumbsService.getFullPath().length == 0)
      this.breadCrumbsService.createFullPath(this.productCategoryId, 'Categories');

    this.getProduct(this.productCategoryId, this.maxResult, this.currentPage, this.needToReceiveAttributes);

  }

  getProduct(productCategoryId:string, maxResult:number, currentPage:number, needToReceiveAttributes:boolean) {
    this.api_ProductCategoryModel.getProductCategoryModels(productCategoryId, maxResult, currentPage, needToReceiveAttributes).subscribe(
      data => this.paginationManagement(data),
      error=>console.log(error),
      () => console.log('ProductCategory Data successfully downloaded!')
    )
  };

  paginationManagement(data) {
    console.log(data);
    if (data.numberOfPages === 0) {
      console.log("Данная категория пуста!")
    }
    else {

      if (this.needToReceiveAttributes == true)
        this.characteristicAttributeList = data.attributeList;

      this.products = data.modelList;

      this.numberOfPages.splice(0);
      for (var i = 0; i < data.numberOfPages; i++) {
        this.numberOfPages.push(i + 1);
      }

      this.needToReceiveAttributes = false;
    }

  }

  setCurrentPage(targetPage:number) {
    this.currentPage = targetPage;
    this.getProduct(this.productCategoryId, this.maxResult, targetPage, this.needToReceiveAttributes);
  }


  navigate(targetProductCategoryId:String, targetProductCategoryName:String) {
    /*this.breadCrumbsService.addPath();*/
    /*this.router.navigate(['ProductCategoryModel', {id: targetProductCategoryName}]);*/
  }
}
