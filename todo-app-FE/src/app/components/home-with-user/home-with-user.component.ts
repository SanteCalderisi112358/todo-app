import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-with-user',
  templateUrl: './home-with-user.component.html',
  styleUrls: ['./home-with-user.component.scss']
})
export class HomeWithUserComponent implements OnInit{
  selectedDate: Date | undefined;

  constructor() {

  }



  ngOnInit(): void {
  }

  getDate(event:Event){
let inputEl = document.querySelector('.input-getDate') as HTMLInputElement
// let newDate = inputEl.value
console.log(this.selectedDate)
  }

}
