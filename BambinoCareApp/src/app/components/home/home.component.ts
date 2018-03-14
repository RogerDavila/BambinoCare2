import {Component, OnInit, ElementRef, ViewChild, AfterViewChecked} from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewChecked {
  @ViewChild('carousel') carousel: ElementRef;
  ngAfterViewChecked(): void {
    console.log($(this.carousel.nativeElement));
    $(this.carousel.nativeElement).carousel({
      interval: 500
    });
  }
  constructor() {}
}
