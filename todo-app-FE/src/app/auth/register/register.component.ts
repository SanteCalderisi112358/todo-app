import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

constructor(private authSrv:AuthService){}
  registra(form:NgForm){
console.log(form.value)
this.authSrv.signup(form.value).subscribe()
  }
}
