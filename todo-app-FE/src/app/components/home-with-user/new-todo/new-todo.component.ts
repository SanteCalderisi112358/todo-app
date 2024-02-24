import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-todo',
  templateUrl: './new-todo.component.html',
  styleUrls: ['./new-todo.component.scss']
})
export class NewTodoComponent {
  selectedDate: Date | undefined;
  selectedTime: Date = new Date();
  constructor() {

  }



  ngOnInit(): void {
  }

  onNewTodo(form:NgForm){
    console.log(form.value)
  }
  getDate(event:Event){
let inputEl = document.querySelector('.input-getDate') as HTMLInputElement
// let newDate = inputEl.value
console.log(this.selectedDate)
  }
}
