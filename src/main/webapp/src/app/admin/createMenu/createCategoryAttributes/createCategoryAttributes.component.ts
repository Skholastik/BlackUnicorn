import {Component,Input} from 'angular2/core';
import {NgClass} from 'angular2/common';

import {API_CharacteristicAttribute} from '../../../globalServices/API/API_CharacteristicAttribute.service';
import {API_Category} from '../../../globalServices/API/API_Category.service';
import {API_View} from '../../../globalServices/API/API_View.service';
import {TextFormatter} from '../../../globalServices/textFormatter.service';
import {BreadCrumbsService} from  '../../../globalServices/breadCrumbsService/breadCrumbs.service';
import {Path} from '../../../globalServices/DTO/path.class.ts';
import {ServiceMessage} from '../../../globalServices/DTO/serviceMessage.class';
import {Category} from '../../../globalServices/DTO/category.class';
import {CharacteristicAttribute} from '../../../globalServices/DTO/characteristicAttribute.class';
import {View} from '../../../globalServices/DTO/view.class';


@Component({
  selector: 'create-category-attributes',
  pipes: [],
  providers: [API_Category, API_CharacteristicAttribute, API_View],
  directives: [NgClass],
  styleUrls: ['../../../../../assets/css/admin/createCategoryAttributes.css'],
  template: require('./createCategoryAttributes.html')
})
export class CreateCategoryAttributes {

  constructor(private api_CharacteristicAttribute:API_CharacteristicAttribute,
              private api_Category:API_Category,
              private api_View:API_View,
              private textFormatter:TextFormatter,
              private breadCrumbsService:BreadCrumbsService) {

  }

  /** Объекты для трансфера */
  private characteristicAttributeList:CharacteristicAttribute[] = [];

  /** Вспомогательные объекты для трансфера */
  private selectedCategory:Category;
  private selectedView:View;
  private selectedAttribute:CharacteristicAttribute;
  private characteristicName:string;
  private unitOfMeasurement:string;
  /** ------------------ */


  private categoryList:Category[];
  private selectedCategoryName:string;

  private viewList:View[];
  private selectedViewType:string;

  private attributeList:CharacteristicAttribute[];
  private selectedAttributeName:string;


  private productMessage:ServiceMessage = new ServiceMessage();
  private categoryMessage:ServiceMessage = new ServiceMessage();
  private attributeMessage:ServiceMessage = new ServiceMessage();
  private characteristicMessage:ServiceMessage = new ServiceMessage();
  private unitOfMeasurementMessage:ServiceMessage = new ServiceMessage();
  private viewMessage:ServiceMessage = new ServiceMessage();
  private creationStatus:ServiceMessage = new ServiceMessage();
  private creationStatusClass:string;


  ngOnInit():void {
    this.getCategoryList();
    this.getViewList();
    this.getCharacteristicAttributeList();

    /*    let path:Path[]=this.breadCrumbsService.getFullPath();
     if (path.length != 0) {
     let lastElement:Path=path[path.length-1];
     if(lastElement.getSignature()==='Category')
     this.testCategory=lastElement;
     }*/
  }


  getCategoryList():void {
    this.api_Category.getCategoryListForCreationModelCategoryProduct().subscribe(
      data => {
        console.log(data);
        this.categoryList = data;
      },
      error =>console.log(error)
    );
  }

  getViewList():void {
    this.api_View.getViewList().subscribe(
      data => {
        console.log(data);
        this.viewList = data;
      },
      error =>console.log(error)
    );
  }

  getCharacteristicAttributeList():void {
    this.api_CharacteristicAttribute.getCharacteristicAttributeList().subscribe(
      data => {
        console.log(data);
        this.attributeList = data;
      },
      error =>console.log(error)
    );
  }

  categorySelected():void {
    this.selectedCategory = this.categoryList.find(category => category.name === this.selectedCategoryName);
  }

  viewSelected():void {
    this.selectedView = this.viewList.find(view => view.type === this.selectedViewType);
  }

  attributeSelected():void {
    this.selectedAttribute = this.attributeList.find(attribute => attribute.name === this.selectedAttributeName);
    console.log(this.selectedAttributeName);
    console.log(this.selectedAttribute);
  }


  addCharacteristicToList(call:string):void {
    if (true) {
      /*let characteristicName = this.textFormatter.wordProcessor(this.characteristicName);*/

      if (call === '1') {
        this.characteristicAttributeList.push(this.selectedAttribute);
        console.log(this.selectedAttribute);
      }
      else {
        /*
         (this.checkCategoryExistenceInListAndDB(categoryName, parentId));
         */
        let newCharacteristic = new CharacteristicAttribute();
        newCharacteristic.name = this.characteristicName;
        newCharacteristic.unitOfMeasurement = this.unitOfMeasurement;
        newCharacteristic.viewType = this.selectedViewType;
        this.characteristicAttributeList.push(newCharacteristic);
      }

    }
  }

  checkAccessToCharacteristicList():boolean {
    let result:boolean = true;

    if (this.characteristicName == undefined) {
      result = false;
      this.characteristicMessage.setInnerMessage(false, 'Необходимо назвать характеристику');
    }
    if (this.unitOfMeasurement == undefined) {
      result = false;
      this.unitOfMeasurementMessage.setInnerMessage(false, 'Необходимо выбрать единицу измерения');
    }
    if (this.selectedViewType == undefined) {
      result = false;
      this.viewMessage.setInnerMessage(false, 'Необходимо выбрать представление');
    }
    if (this.characteristicName != undefined)
      this.characteristicMessage.setInnerMessage(true, '');
    if (this.unitOfMeasurement != undefined)
      this.unitOfMeasurementMessage.setInnerMessage(true, '');
    if (this.selectedViewType != undefined)
      this.viewMessage.setInnerMessage(true, '');

    return result;
  }

  addProductCategoryModelAttributes():void {

    if (this.checkAccessToCharacteristicListTransfer()) {
      this.api_Category.addCategoryAttributes(this.characteristicAttributeList, this.selectedCategory.id).subscribe(
        data => {
          console.log(data);
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

  checkAccessToCharacteristicListTransfer():boolean {
    let result:boolean = true;

    if (this.selectedCategoryName == undefined) {
      result = false;
      this.categoryMessage.setInnerMessage(false, 'Необходимо выбрать категорию');
    }

    if (this.characteristicAttributeList.length === 0) {
      result = false;
      this.creationStatus.setInnerMessage(false, 'Необходимо создать хотя бы 1 характеристику')
    }

    if (this.selectedCategoryName != undefined)
      this.categoryMessage.setInnerMessage(true, '');
    if (this.characteristicAttributeList != undefined)
      this.creationStatus.setInnerMessage(true, 'Когда будет создана таблица, перенеси служебное сообщение под нее');

    return result;
  }

  removeCharacteristicFromList(name:String):void {
    for (var i = 0; i < this.characteristicAttributeList.length; i++)
      if (this.characteristicAttributeList[i].name === name) {
        this.characteristicAttributeList.splice(i, 1)
      }
  }

  /*  checkCategoryExistenceInListAndDB(categoryName:string, parentId:string):boolean {

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
   }*/
}
