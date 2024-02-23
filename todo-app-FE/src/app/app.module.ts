import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeNoUserComponent } from './components/home-no-user/home-no-user.component';
import { HomeWithUserComponent } from './components/home-with-user/home-with-user.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import {RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { BsDatepickerModule, BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

const routes:Routes =[
 {
  path: 'home-no-user',
  component:HomeNoUserComponent,
 },
 {
  path: 'home-with-user',
  component:HomeWithUserComponent,
 },
 {
  path: 'login',
  component:LoginComponent,
 },
 {
  path: 'register',
  component:RegisterComponent,
 },


]
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeNoUserComponent,
    HomeWithUserComponent,
    LoginComponent,
    RegisterComponent,

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    TooltipModule.forRoot(),
    BsDatepickerModule.forRoot(),
    FormsModule,
    HttpClientModule,

  ],
  providers: [BsDatepickerConfig],
  bootstrap: [AppComponent]
})
export class AppModule { }
