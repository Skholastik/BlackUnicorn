import {Injectable,Inject,OnInit} from "angular2/core";
import {Http, Headers,URLSearchParams} from "angular2/http";
import {API_BreadCrumbs} from "./API_BreadCrumbs.service.ts";

import {Path} from '../DTO/path.class.ts';


@Injectable()
export class BreadCrumbsService {


  private fullPath:Path [] = [];

  /** Уникальный ключ для каждого Path в массиве fullPath. Служит идентификатором уникальности пути*/
  private generatedUniqueKeyForPath:number = 1;

  constructor(@Inject(API_BreadCrumbs) private api_BreadCrumbs:API_BreadCrumbs) {
  };

  getFullPath():Path[] {
    return this.fullPath;
  }

  addPath(routeId:string, name:string, signature:string):Path {
    let newPath:Path;
    for (let i = 0; i < this.fullPath.length; i++) {
      if (this.fullPath[i].getName() == name && this.fullPath[i].getSignature() == signature) {
        newPath = this.fullPath[i];
        this.cleaningPath(newPath);
        return newPath
      }
    }
    newPath = new Path(this.generatedUniqueKeyForPath, routeId, name, signature);
    this.fullPath.push(newPath);
    this.generatedUniqueKeyForPath++;
    return newPath;
  }


  /** Чистит навигационную цепочку ниже по иерархии при переходе на ссылки выше по иерархии */
  cleaningPath(path:Path):void {
    for (var i = 0; i < this.fullPath.length; i++) {
      if (this.fullPath[i].getUniqueKey() === path.getUniqueKey()) {
        this.generatedUniqueKeyForPath = this.fullPath[i].getUniqueKey() + 1;
        this.fullPath.splice(i + 1);
        break;
      }
    }
  }

  /** Создает полную навигационную цепочку до данной таблицы сущностей, возвращает boolean
   результат операции,в том случае, если не было последовательного  перехода между таблицами
   сущностей,т.е фактически создает возможность при переходе на ссылке на данный ресурс, корректное
   выстраивание всей иерархической цепочки */

  createFullPath(routeParamsId:string,signature:string):boolean {
    let resultOfOperation:boolean = false;
    this.getBasePath(signature);

    if(routeParamsId!='0' && routeParamsId!=undefined) {
      this.api_BreadCrumbs.createFullPath(routeParamsId).subscribe(
        data=> {
          for (let i = 0; i < data.length; i++) {
            this.addPath(data[i].id, data[i].name, data[i].category);
          }
          resultOfOperation = true;
        },
        error=> {
          console.log(error);
          resultOfOperation = false;
        },
        ()=>console.log('All actions complete!')
      )
    }
    return resultOfOperation;

  }

  /** Создает базовый путь, т.е который был до BreadCrumbs и внедряет его в BreadCrumbs */
  getBasePath(signature:String){
    if(signature==='Tables' || signature==='Categories' )
      this.addPath(undefined, 'Таблицы', 'Tables');
    if(signature==='Categories'){
      this.addPath('0', 'Категории', 'Category');
    }
  }
}
