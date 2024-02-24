import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthData } from 'src/app/auth/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';
import { Todo } from 'src/app/models/todo.interface';
import { TodoService } from 'src/app/service/todo.service';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.scss']
})
export class TodosComponent implements OnInit{
todosByUserId:Todo[] = []
userId:string | undefined
user!: AuthData|null
token:string | undefined
subTodosByUserId:Subscription | undefined

constructor(private todoSrv:TodoService, private autSrv:AuthService){}
ngOnInit(): void {
  this.autSrv.user$.subscribe((_user) => {
    this.user = _user;
    this.userId = _user?.user.id;
    this.token = _user?.accessToken;
console.log(this.token)
    if (this.userId && this.token) {
      // Crea un oggetto HttpHeaders con il token Bearer

      // Effettua la richiesta GET con gli header inclusi
      this.subTodosByUserId = this.todoSrv.getAllTodosByUserId(this.userId).subscribe((response) => {
        this.todosByUserId = response;
        console.log(this.todosByUserId);
      });
    }
  });
}

}
