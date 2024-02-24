import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { User } from "../models/user.interface";
import { AuthData } from "./auth-data.interface";
import { BehaviorSubject, tap } from "rxjs";
import { Router } from "@angular/router";
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root',
})
export class AuthService{
  baseUrl = environment.baseURL;
  jwtHelper = new JwtHelperService();
  timeLogout: any;

  private authSubj = new BehaviorSubject<null | AuthData>(null);
  user$ = this.authSubj.asObservable();
user:AuthData | undefined;
utenteLoggato:any;
datiUtente: AuthData ={
  accessToken: "",
  user: {
  email:"",
  name:"",
  last_name:"",
  id:""
  }
}
userProfile!: User;

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
        this.router.navigate(['/']);
         this.authSubj.next(data);
        this.user = data;
        console.log(this.user);
        console.log(data)
        localStorage.setItem('utente', JSON.stringify(data));
        this.autologout(data);
        this.userProfile = data.utente;
      })
    );
  }

  logout() {
    this.authSubj.next(null);
    localStorage.removeItem('utente');
    this.router.navigate(['home-no-user']);
    if (this.timeLogout) {
      clearTimeout(this.timeLogout);
    }
  }

  restore() {
    this.utenteLoggato = localStorage.getItem('utente');
   if (!this.utenteLoggato) {
       return;
   }

   this.datiUtente = JSON.parse(this.utenteLoggato);
   if (this.jwtHelper.isTokenExpired(this.datiUtente.accessToken)) {
       this.logout;
   }
   else {
     this.authSubj.next(this.datiUtente);
     this.userProfile = this.datiUtente.user;
     this.autologout(this.datiUtente);
   }
}

autologout(data: AuthData) {
  const expirationDate = this.jwtHelper.getTokenExpirationDate(
    data.accessToken
  ) as Date;
  const expirationMilliseconds =
    expirationDate.getTime() - new Date().getTime();
  this.timeLogout = setTimeout(() => {
    this.logout();
  }, expirationMilliseconds);
}

}
