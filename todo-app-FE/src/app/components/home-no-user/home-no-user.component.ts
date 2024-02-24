import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user.interface';
import { TodoService } from 'src/app/service/todo.service';

@Component({
  selector: 'app-home-no-user',
  templateUrl: './home-no-user.component.html',
  styleUrls: ['./home-no-user.component.scss']
})
export class HomeNoUserComponent implements OnInit{
  allUser:User[]=[]
  subUser:Subscription | undefined
constructor(private todoSrv: TodoService){}
  ngOnInit(): void {
    // this.subUser = this.todoSrv.getAllUtenti().subscribe((response)=>{
    //   this.allUser = response
    //   console.log(this.allUser)
    // })
  }

}
