import {Component,OnInit} from 'angular2/core';
import {RouteConfig, Router, ROUTER_DIRECTIVES} from 'angular2/router';

import {BreadCrumbsService} from '../.././globalServices/breadCrumbsService/breadCrumbs.service';
import {Path} from '../.././globalServices/DTO/path.class';

@Component({
  selector: 'tables',
  pipes: [],
  providers: [],
  directives: [ROUTER_DIRECTIVES],
  styleUrls: ['../../../assets/css/admin/tables.css'],
  template: require('./tables.html')
})
export class Tables {

  constructor(private router:Router,
              private breadCrumbsService:BreadCrumbsService) {
  };

  ngOnInit() {

    if (this.breadCrumbsService.getFullPath().length === 0) {
      this.breadCrumbsService.createFullPath(undefined,'Tables');
    }

  }

  navigateTable(startId:string,tableName:string, signature:string) {
    let newPath = this.breadCrumbsService.addPath(startId, tableName, signature);
    this.router.navigate([newPath.getSignature(), {id: '0'}]);
    console.log(this.breadCrumbsService.getFullPath().length);
  }
}


