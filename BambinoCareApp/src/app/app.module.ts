import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {HeaderIndexComponent} from './components/header-index/header-index.component';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

const ROUTES: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderIndexComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES),
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
