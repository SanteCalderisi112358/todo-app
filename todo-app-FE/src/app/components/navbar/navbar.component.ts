import { Component } from '@angular/core';
import { AuthData } from 'src/app/auth/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
collapsed:boolean=true
user!: AuthData|null
  constructor(private autSrv:AuthService) { }

  ngOnInit(): void {
    this.autSrv.user$.subscribe((_user)=>{
      this.user= _user
      if(this.user){
        console.log(this.user.user)

      }else{
        console.log("Nessun si è loggato")
      }

    })
  }



  logout(){
    this.autSrv.logout()
  }



closeMenu(){
  this.collapsed = true
}
}
