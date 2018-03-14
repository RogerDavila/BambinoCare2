import {Component, OnInit, ElementRef, ViewChild, AfterViewChecked} from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  loginButtonStr: string = "Iniciar Sesión";
  title: string = "Bienvenido a Bambino Care";
  constructor() {}
}
