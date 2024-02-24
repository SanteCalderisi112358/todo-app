import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { User } from "../models/user.interface";
import { Todo } from "../models/todo.interface";
import { AuthData } from "../auth/auth-data.interface";


@Injectable({
  providedIn: 'root',
})
export class TodoService{
  baseUrl = environment.baseURL;
  constructor(private http: HttpClient) {}


  getAllUtenti(){
    return this.http.get<User[]>(`${this.baseUrl}users`);
  }

  getAllTodosByUserId(userId: string) {
    return this.http.get<Todo[]>(`${this.baseUrl}todos/user=${userId}`);
  }


}
