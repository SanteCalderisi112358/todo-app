import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { User } from "../models/user.interface";
import { AuthData } from "./auth-data.interface";
import { BehaviorSubject, tap } from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthService{
  baseUrl = environment.baseURL;
  private authSubj = new BehaviorSubject<null | AuthData>(null);
  user$ = this.authSubj.asObservable();

  constructor(private http:HttpClient){}

  signup(data: {
    nome: string;
    cognome: string;
    email: string;
    password: string;
  }) {
    return this.http.post(`${this.baseUrl}auth/register`, data);
  }

  login(data: User) {
    return this.http.post<AuthData>(`${this.baseUrl}auth/login`, data).pipe(
      tap((data: any) => {
        console.log(data);
        // this.router.navigate(['/']);
         this.authSubj.next(data);
        // this.utente = data;
        // console.log(this.utente);
        console.log(data)
        localStorage.setItem('utente', JSON.stringify(data));
        // this.autologout(data);
        // this.userProfile = data.utente;
      })
    );
  }

  logout() {
    this.authSubj.next(null);
    localStorage.removeItem('utente');
    // this.router.navigate(['/']);
    // if (this.timeLogout) {
    //   clearTimeout(this.timeLogout);
    // }
  }
}
