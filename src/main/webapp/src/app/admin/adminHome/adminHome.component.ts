import {Component,OnInit} from 'angular2/core';

@Component({
  selector: 'admin-home',
  pipes: [],
  providers: [],
  directives: [],
  styleUrls: ['../../assets/css/admin/adminHome.css'],
  template: require('./adminHome.html')
})

export class AdminHome implements OnInit {

  ngOnInit() {
    console.log('hello `AdminHome!` component');
  }

}


