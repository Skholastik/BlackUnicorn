import {Component,OnInit, ElementRef,Renderer} from 'angular2/core';
import {RouteConfig, Router, ROUTER_DIRECTIVES} from 'angular2/router';
import {NgClass} from 'angular2/common';

import {BreadCrumbsService} from '.././globalServices/breadCrumbsService/breadCrumbs.service';
import {Path} from '.././globalServices/DTO/path.class';


@Component({
  selector: 'admin',
  pipes: [],
  providers: [BreadCrumbsService],
  directives: [ROUTER_DIRECTIVES, NgClass],
  styleUrls: ['../../assets/css/admin/adminRoot.css'],
  template: require('./adminRoot.html')
})

@RouteConfig([
  {
    path: '/',
    name: 'AdminHome',
    loader: () => require('es6-promise!./adminHome/adminHome.component')('AdminHome'),
    useAsDefault: true
  },
  {path: '/tables', name: 'Tables', loader: () => require('es6-promise!./tables/tables.component')('Tables')},

  {
    path: '/tables/categories/:id',
    name: 'Category',
    loader: () => require('es6-promise!./tables/categories/category/category.component')('Category')

  },
  {
    path: '/tables/categories/productCategoryModel/:categoryId',
    name: 'ProductCategoryModel',
    loader: () => require('es6-promise!./tables/categories/productCategoryModel/productCategoryModel.component')('ProductCategoryModel')
  }
  ,
  {
    path: '/create/category',
    name: 'CreateCategory',
    loader: () => require('es6-promise!./createMenu/createCategory/createCategory.component')('CreateCategory')
  }  ,
  {
    path: '/create/categoryAttributes',
    name: 'CreateCategoryAttributes',
    loader: () => require('es6-promise!./createMenu/createCategoryAttributes/createCategoryAttributes.component')('CreateCategoryAttributes')
  }
])

export class AdminRoot implements OnInit {

  targetClassForSideBar:String = 'container-sidebar-open';
  targetClassForHeader:String = 'open-menu-header';

  constructor(private router:Router,
              private breadCrumbsService:BreadCrumbsService) {
  };

  private fullPath:Path[]=[];
  private activeDropDownMenu:boolean=false;

  ngOnInit() {
    this.fullPath = this.breadCrumbsService.getFullPath();
  }

  navigateMenu(name:string, signature:string) {
    let newPath = this.breadCrumbsService.addPath(undefined, name, signature);
    this.router.navigate(['./' + newPath.getSignature()]);
  }

  navigateBreadCrumbs(path:Path) {
    this.breadCrumbsService.cleaningPath(path);

    if (path.getId() == undefined)
      this.router.navigate(['./' + path.getSignature()]);
    else
      this.router.navigate(['./' + path.getSignature(), {id: path.getId()}]);

  }


  /** Меняет классы у бокового меню. Тем самым обеспечивает его функционалом, связанным
   с анимацией движения в бок и последующим скрытием  */
  openCloseMenu() {
    if (this.targetClassForSideBar === 'container-sidebar-open') {
      this.targetClassForSideBar = 'container-sidebar-close';
      this.targetClassForHeader = 'close-menu-header';
    }
    else {
      this.targetClassForSideBar = 'container-sidebar-open';
      this.targetClassForHeader = 'open-menu-header';
    }
  }

  openCloseDropDownMenu(){
    this.activeDropDownMenu=this.activeDropDownMenu ==false ? true : false;
  }
}


