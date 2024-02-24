import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { User } from "../models/user.interface";
import { AuthData } from "./auth-data.interface";
import { BehaviorSubject, tap } from "rxjs";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root',
})
export class AuthService{
  baseUrl = environment.baseURL;
  private authSubj = new BehaviorSubject<null | AuthData>(null);
  user$ = this.authSubj.asObservable();
user:AuthData | undefined;
  constructor(private http:HttpClient, private router:Router){}

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
    this.router.navigate(['home-no-user']);

  }

  restore() {
    let userFromLocalStorageStringify = localStorage.getItem('utente')
   if (!userFromLocalStorageStringify) {
       return;
   }
   this.user = JSON.parse(userFromLocalStorageStringify);
   if (!this.user?.accessToken) {
       this.logout;
   }
 if(this.user)
     this.authSubj.next(this.user);
  //    this.userProfile = this.datiUtente.utente;
  //    this.autologout(this.datiUtente);

  userFromLocalStorageStringify = ''
  this.user = undefined
}
}
