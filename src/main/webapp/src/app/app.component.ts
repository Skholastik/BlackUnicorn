/*
 * Angular 2 decorators and services
 */
import {Component} from 'angular2/core';
import {RouteConfig, Router, ROUTER_DIRECTIVES} from 'angular2/router';

import {Home} from './home/home.component';
import {AdminRoot} from './admin/adminRoot.component';

/*
 * App Component
 * Top Level Component
 */
@Component({
  selector: 'app',
  pipes: [],
  providers: [],
  directives: [ROUTER_DIRECTIVES],
  styles: [],
  template: require('./app.html')
})
@RouteConfig([
  {path: '/', name: 'Index', component: Home},
  {path: '/home', name: 'Home', component: Home, useAsDefault: true},
  {
    path: '/admin/...',
    name: 'AdminRoot',
    loader: () => require('es6-promise!./admin/adminRoot.component')('AdminRoot')
  }

  // Async load a component using Webpack's require with es6-promise-loader and webpack `require`


])
export class App {
  angularclassLogo = 'dist/assets/img/angularclass-avatar.png';
  name = 'Angular 2 Webpack Starter';
  url = 'https://twitter.com/AngularClass';

  constructor() {
  }
}
