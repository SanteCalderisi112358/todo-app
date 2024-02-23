import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { User } from "../models/user.interface";


@Injectable({
  providedIn: 'root',
})
export class TodoService{
  baseUrl = environment.baseURL;
  constructor(private http: HttpClient) {}


  getAllUtenti(){
    return this.http.get<User[]>(`${this.baseUrl}users`);
  }


}
