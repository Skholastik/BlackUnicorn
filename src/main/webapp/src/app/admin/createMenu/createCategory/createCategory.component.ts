import {Component,Input} from 'angular2/core';
import {NgClass} from 'angular2/common';

import {TextFormatter} from '../../../globalServices/textFormatter.service';
import {BreadCrumbsService} from  '../../../globalServices/breadCrumbsService/breadCrumbs.service';
import {ServiceMessage} from '../../../globalServices/DTO/serviceMessage.class';
import {Category} from '../../../globalServices/DTO/category.class';
import {API_Category} from '../../../globalServices/API/API_Category.service';
import {Path} from '../../../globalServices/DTO/path.class.ts';


@Component({
  selector: 'create-category',
  pipes: [],
  providers: [API_Category],
  directives: [NgClass],
  styleUrls: ['../../../../../assets/css/admin/createCategory.css'],
  template: require('./createCategory.html')
})
export class CreateCategory {

  constructor(private api_Category:API_Category,
              private textFormatter:TextFormatter,
              private breadCrumbsService:BreadCrumbsService) {

  }

  private categoryList:Category[];
  private selectedCategoryName:string;

  private newCategoryName:string;
  private selectedCategory:Category;
  private newCategoryList:Category[] = [];

  private categoryMessage:ServiceMessage = new ServiceMessage();
  private creationStatus:ServiceMessage = new ServiceMessage();
  private creationStatusClass:string;



  ngOnInit():void {
    this.getCategoryListForCreationCategory();
/*    let path:Path[]=this.breadCrumbsService.getFullPath();
    if (path.length != 0) {
      let lastElement:Path=path[path.length-1];
      if(lastElement.getSignature()==='Category')
        this.testCategory=lastElement;
    }*/


  }

  getCategoryListForCreationCategory():void {
    this.api_Category.getCategoryListForCreationCategory().subscribe(
      data => {
        console.log(data);
        this.categoryList = data;
      },
      error =>console.log(error)
    );
  }

  categorySelected():void {
    this.selectedCategory = this.categoryList.find(category => category.name === this.selectedCategoryName);
    console.log(this.selectedCategory);
  }


  addNewCategoryToList():void {
    if (this.newCategoryName != undefined) {
      let categoryName = this.textFormatter.wordProcessor(this.newCategoryName);
      let parentId = this.selectedCategory == undefined ? '0' : this.selectedCategory.id;

      if (true) {
        (this.checkCategoryExistenceInListAndDB(categoryName, parentId));
        let newCategory = new Category();
        newCategory.setName(categoryName);
        this.newCategoryList.push(newCategory);
      }
    }
  }

  addCategories():void {
    if (this.newCategoryName != undefined) {

      let parentId = this.selectedCategory == undefined ? '0' : this.selectedCategory.id;

      this.api_Category.addCategoryList(parentId, this.newCategoryList).subscribe(
        data => {
          this.creationStatus.fillFromJSON(data);
          this.creationStatusClass = 'creation_status_success';
        },
        error => {
          this.creationStatus.setInnerMessage(false, 'Произошла непредвиденная ошибка!');
          this.creationStatusClass = 'creation_status_fail';
          console.log(error)
        },
        () => console.log('Transfer Complete!')
      )
    }
  }

  checkCategoryExistenceInListAndDB(categoryName:string, parentId:string):boolean {

    if (this.newCategoryList.length != 0) {
      for (var i = 0; i < this.newCategoryList.length; i++) {
        if (this.newCategoryList[i].name === categoryName) {
          this.categoryMessage.setInnerMessage(false, 'В списке уже имеется категория с таким именем');
          return false;
        }
      }
    }
    this.api_Category.checkCategoryExistence(parentId, categoryName).subscribe(
      data => {
        this.categoryMessage.fillFromJSON(data);
        return this.categoryMessage.getResult();
      },
      error=> {
        console.log(error);
        this.categoryMessage.setInnerMessage(false, 'Произошла непредвиденная ошибка, подробнее в смотрите в консоли(F12)');
      }
    )
  }

  removeCategoryFromList(name:String):void {
    for (var i = 0; i < this.newCategoryList.length; i++)
      if (this.newCategoryList[i].name === name) {
        this.newCategoryList.splice(i, 1)
      }
  }
}
