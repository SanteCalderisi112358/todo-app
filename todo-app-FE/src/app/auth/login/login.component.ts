import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  errore:string | undefined;
  isLoading = false;
  erroreLogin = false;
  isErroreRecuperoPassword:boolean = true; ;
  erroreRecuperoPassword:string = ''
  messaggioOk:string =''
  emailUtente:string = '';
  isModaleOpen:boolean=false;
  loginForm:NgForm | undefined;
constructor(private authServ:AuthService, private router:Router){}

access(form:NgForm){
  this.loginForm = form
      setTimeout(() => {
        this.isLoading = true;
      }, 1500);
      console.log(this.loginForm.value)
      try {
        this.authServ.login(this.loginForm.value).subscribe(
          () => {
            this.router.navigate(['/home-with-user']);
            this.isLoading = false;
          },
          (error) => {
         this.errore = error.error.message;
         if(error.error.message[0]==='U'){
          this.errore = 'La tua email non è stata trovata. Registrati!'
         }
         console.log(this.errore)
         if(this.errore!==null){
          this.erroreLogin = true;
         }
            this.isLoading = false;
          }
        );
      } catch (error: any) {
        console.error(error);
        this.isLoading = false;
      }



    }


}
