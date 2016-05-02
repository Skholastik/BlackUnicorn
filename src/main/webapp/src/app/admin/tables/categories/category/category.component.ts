import {Component,OnInit,Input} from 'angular2/core';
import {Router,RouteParams} from 'angular2/router';
import {NgClass} from 'angular2/common';


import {API_Category} from '../../../../globalServices/API/API_Category.service.ts'
import {BreadCrumbsService} from  '../../../.././globalServices/breadCrumbsService/breadCrumbs.service';
import {Category} from '../../../.././globalServices/DTO/category.class';
import {Path} from '../../../.././globalServices/DTO/path.class';


@Component({
  selector: 'category',
  pipes: [],
  providers: [API_Category],
  directives: [NgClass],
  styleUrls: ['../../../../assets/css/admin/category.css'],
  template: require('./category.html')
})
export class Category implements OnInit {

  constructor(private api_Category:API_Category,
              private router:Router,
              private routeParams:RouteParams,
              private breadCrumbsService:BreadCrumbsService) {
  }

  categoryList:Category[];


  ngOnInit() {
    let id = this.routeParams.get('id');

    if (id == null) {
      id = '0';
    }

    if (this.breadCrumbsService.getFullPath().length === 0)
      this.breadCrumbsService.createFullPath(id,'Categories');

    this.getCategoryByParentId(id);

  }

  getCategoryByParentId(parentId:string) {
    this.api_Category.getCategory(parentId).subscribe(
      data => {

        if (data.length == 0)
          console.log('Можно добавить категории!');

        this.categoryList = data;
      },
      error=>console.log(error)
    )
  }

  navigate(category:Category) {

    if (category.hasProductCategoryModel) {
      let newPath = this.breadCrumbsService.addPath(category.id, category.name, 'ProductCategoryModel');
      this.router.navigate([newPath.getSignature(), {categoryId: category.id}]);
    }
    else {
      let newPath = this.breadCrumbsService.addPath(category.id, category.name, 'Category');
      this.router.navigate([newPath.getSignature(), {id: category.id}]);

    }
  }
}


