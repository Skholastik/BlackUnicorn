/*
 * These are globally available services in any component or any other service
 */


import {provide} from 'angular2/core';

// Angular 2
import {FORM_PROVIDERS} from 'angular2/common';

// Angular 2 Http
import {HTTP_PROVIDERS} from 'angular2/http';
// Angular 2 Router
import {ROUTER_PROVIDERS, LocationStrategy, PathLocationStrategy} from 'angular2/router';

/** Найти способ ипортировать сервис не с верхней точки иерархии, т.к он нужен только в админке*/
import {API_BreadCrumbs} from '../../app/globalServices/breadCrumbsService/API_BreadCrumbs.service';

/** Глобальный сервис для форматирования текста */

import {TextFormatter} from '../../app/globalServices/textFormatter.service';


// Angular 2 Material
// import {MdRadioDispatcher} from '@angular2-material/radio/radio_dispatcher';
// const MATERIAL_PROVIDERS = [
//   MdRadioDispatcher
// ];

/*
* Application Providers/Directives/Pipes
* providers/directives/pipes that only live in our browser environment
*/
export const APPLICATION_PROVIDERS = [
  ...FORM_PROVIDERS,
  ...HTTP_PROVIDERS,
  // ...MATERIAL_PROVIDERS,
  ...ROUTER_PROVIDERS,
  provide(LocationStrategy, { useClass: PathLocationStrategy }),
];

export const PROVIDERS = [
  ...APPLICATION_PROVIDERS,
  API_BreadCrumbs,TextFormatter
];
