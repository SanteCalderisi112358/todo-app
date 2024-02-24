import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  isLoading = false;
  errori: string[] =[];
  errore: string ="";
constructor(private authSrv:AuthService, private router: Router){}
registra(form: NgForm) {
  this.isLoading = true;
  try {
    this.authSrv.signup(form.value).subscribe(
      () => {
        this.router.navigate(['/login']);
        this.isLoading = false;
      },
      (error) => {
        if(error.error.errorsList){
          console.error(error)
          this.errori = error.error.errorsList
          setTimeout(() => {
            this.isLoading = true;
          }, 500);
        }else if(error.error.message){
          console.log(error.error.message)
          this.errore = error.error.message
          setTimeout(() => {
            this.isLoading = true;
          }, 500);
        }
        /*
        this.errori = error.error.errorsList;
        console.error(this.errori);*/
        this.isLoading = false;

      }
    );
  } catch (error) {
    console.error(error);
    this.isLoading = false;
  }

  this.errore =""
  this.errori = []
}
}
