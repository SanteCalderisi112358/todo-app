import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
constructor(private authSrv:AuthService){}

access(form:NgForm){
console.log(form.value)
this.authSrv.login(form.value).subscribe()

}


}
