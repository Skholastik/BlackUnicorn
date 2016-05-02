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

  constructor(private api_Attribute:API_CharacteristicAttribute,
              private api_Category:API_Category,
              private api_View:API_View,
              private textFormatter:TextFormatter,
              private breadCrumbsService:BreadCrumbsService) {

  }

  /** Объекты для трансфера */
  private characteristicAttributeList:CharacteristicAttribute[] = [];

  /** Вспомогательные объекты для трансфера */
  private selectedCategory:Category;
  private selectedDbAttribute:CharacteristicAttribute;
  private newAttributeName:string;
  private newUnitOfMeasurement:string;
  private selectedView:View;
  /** ------------------ */


  private categoryList:Category[];
  private selectedCategoryName:string;

  private viewList:View[];
  private selectedViewType:string;

  private attributeDbList:CharacteristicAttribute[];
  private selectedDbAttributeName:string;

  private categoryMessage:ServiceMessage = new ServiceMessage();
  private attributeDbMessage:ServiceMessage = new ServiceMessage();
  private newAttributeNameMessage:ServiceMessage = new ServiceMessage();
  private newAttributeUnitOfMeasurementMessage:ServiceMessage = new ServiceMessage();
  private attributeViewTypeMessage:ServiceMessage = new ServiceMessage();
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
    this.api_Attribute.getCharacteristicAttributeList().subscribe(
      data => {
        console.log(data);
        this.attributeDbList = data;
      },
      error =>console.log(error)
    );
  }

  addDbCharacteristicToList():void {
    if (this.selectedCategory == undefined)
      this.categoryMessage.setInnerMessage(false, 'Необходимо выбрать категорию');

    if (this.selectedDbAttribute != undefined) {
      for (var i = 0; i < this.characteristicAttributeList.length; i++) {
        if (this.characteristicAttributeList[i].id == this.selectedDbAttribute.id) {
          this.attributeDbMessage.setInnerMessage(false, 'В списке уже имеется данный атрибут!');
          return;
        }
      }
      this.characteristicAttributeList.push(this.selectedDbAttribute);
      this.attributeDbMessage.setInnerMessage(true, '');
    }
    else
      this.attributeDbMessage.setInnerMessage(false, 'Выберите аттрибут');

  }

  addNewCharacteristicToList():void {

    if (this.checkAccessToCharacteristicList()) {
      let characteristicName = this.textFormatter.wordProcessor(this.newAttributeName);

      if (this.checkCategoryExistenceInListAndDB(characteristicName, this.newUnitOfMeasurement, this.selectedCategory.id)) {
        let newCharacteristic = new CharacteristicAttribute();
        newCharacteristic.name = characteristicName;
        newCharacteristic.unitOfMeasurement = this.newUnitOfMeasurement;
        newCharacteristic.viewType = this.selectedViewType;
        this.characteristicAttributeList.push(newCharacteristic);
      }
    }
  }

  checkAccessToCharacteristicList():boolean {
    let result:boolean = true;

    if (this.selectedCategory == undefined) {
      result = false;
      this.categoryMessage.setInnerMessage(false, 'Необходимо выбрать категорию');
    }
    if (this.newAttributeName == undefined || this.newAttributeName == '') {
      result = false;
      this.newAttributeNameMessage.setInnerMessage(false, 'Необходимо назвать характеристику');
    }
    if (this.newUnitOfMeasurement == undefined || this.newUnitOfMeasurement == '') {
      result = false;
      this.newAttributeUnitOfMeasurementMessage.setInnerMessage(false, 'Необходимо выбрать единицу измерения');
    }
    if (this.selectedView == undefined) {
      result = false;
      this.attributeViewTypeMessage.setInnerMessage(false, 'Необходимо выбрать представление');
    }
    return result;
  }


  checkCategoryExistenceInListAndDB(attributeName:string, unitOfMeasurement:string, parentId:string):boolean {

    for (var i = 0; i < this.characteristicAttributeList.length; i++) {
      if (this.characteristicAttributeList[i].name === attributeName &&
        this.characteristicAttributeList[i].unitOfMeasurement === unitOfMeasurement) {
        this.categoryMessage.setInnerMessage(false, 'В списке уже имеется атрибуты с данными параметрами');
        return false;
      }
    }
    this.api_Category.checkCategoryAttributeExistence(attributeName, unitOfMeasurement, parentId).subscribe(
      data => {
        console.log(data);
        this.categoryMessage.fillFromJSON(data);
        return this.categoryMessage.getResult();
      },
      error=> {
        console.log(error);
        this.categoryMessage.setInnerMessage(false, 'Произошла непредвиденная ошибка, подробнее  смотрите в консоли(F12)');
      }
    )
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
    else
      this.categoryMessage.setInnerMessage(true, '');

    if (this.characteristicAttributeList.length === 0) {
      result = false;
      this.creationStatus.setInnerMessage(false, 'Необходимо создать хотя бы 1 характеристику')
    }
    else
      this.creationStatus.setInnerMessage(true, 'Когда будет создана таблица, перенеси служебное сообщение под нее');

    return result;
  }

  removeCharacteristicFromList(name:String):void {
    for (var i = 0; i < this.characteristicAttributeList.length; i++)
      if (this.characteristicAttributeList[i].name === name) {
        this.characteristicAttributeList.splice(i, 1)
      }
  }

  categorySelected():void {
    this.selectedCategory = this.categoryList.find(category => category.name === this.selectedCategoryName);
    if (this.characteristicAttributeList.length != 0)
      this.characteristicAttributeList.splice(0, this.characteristicAttributeList.length);
    if (this.selectedCategory == undefined)
      this.categoryMessage.setInnerMessage(false, 'Необходимо выбрать категорию');
    else
      this.categoryMessage.setInnerMessage(true, '');

  }

  attributeDbSelected():void {
    this.selectedDbAttribute = this.attributeDbList.find(attribute => attribute.name === this.selectedDbAttributeName);
  }

  viewSelected():void {
    this.selectedView = this.viewList.find(view => view.type === this.selectedViewType);
    if (this.selectedView == undefined)
      this.attributeViewTypeMessage.setInnerMessage(false, 'Необходимо выбрать представление');
    else
      this.attributeViewTypeMessage.setInnerMessage(true, '');
  }

  newAttributeNameChanged():void {
    if (this.newAttributeName == undefined || this.newAttributeName == '')
      this.newAttributeNameMessage.setInnerMessage(false, 'Необходимо назвать характеристику');
    else
      this.newAttributeNameMessage.setInnerMessage(true, '');
  }

  newAttributeUnitOfMeasurementChanged():void {
    if (this.newUnitOfMeasurement == undefined || this.newUnitOfMeasurement == '')
      this.newAttributeUnitOfMeasurementMessage.setInnerMessage(false, 'Необходимо выбрать единицу измерения');
    else
      this.newAttributeUnitOfMeasurementMessage.setInnerMessage(true, '');
  }
}
