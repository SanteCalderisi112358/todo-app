/*File che serve per intercettare il token*/
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { switchMap, take} from 'rxjs/operators'
import { AuthData } from './auth-data.interface';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  user:AuthData | undefined
  constructor(private authSrv:AuthService) {}

  newReq!: HttpRequest<any>
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.authSrv.user$.pipe(take(1), switchMap(user =>{
      console.log(user)
      if(!user){
        console.log(user)
        console.log(this.newReq)
        return next.handle(request)
      }else{
        this.newReq = request.clone({
          headers: request.headers.set(`Authorization`, `Bearer ${user.accessToken}` )

        })
        console.log(request)
      console.log(this.newReq.headers.get('Authorization'))
      }

      return next.handle(this.newReq)
    }));
  }
}
