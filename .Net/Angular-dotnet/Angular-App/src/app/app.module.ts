import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule, Router } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { propertyCardCompnent } from './Property/Property-card/Property-card.component';
import { PropertyListComponent } from './Property/property-list/property-list.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HttpClientModule } from '@angular/common/http';
import { HousingService } from './Service/housing.service';
import { AddPropertyComponent } from './Property/add-property/add-property.component';
import { PropertyDetailComponent } from './Property/property-detail/property-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserLoginComponent } from './users/user-login/user-login.component';
import { UserRegisterComponent } from './users/user-register/user-register.component';
import { UserServiceService } from './Service/user-service.service';

const appRoutes: Routes = [
  {
    path: 'add-property',
    component: AddPropertyComponent
  },
  {
    path: '',
    component: PropertyListComponent
  },
  {
    path: 'rent-property',
    component: PropertyListComponent
  },
  {
    path: 'property-details/:id',
    component: PropertyDetailComponent
  },
  {
    path: 'users/login',
    component: UserLoginComponent
  }, {
    path: 'users/register',
    component: UserRegisterComponent
  },
  {
    path: '**', // this will be default route for all the unknown routes
    component: PropertyListComponent
  },
]


@NgModule({
  declarations: [
    AppComponent,
    propertyCardCompnent,
    PropertyListComponent,
    NavBarComponent,
    PropertyDetailComponent,
    AddPropertyComponent,
    UserLoginComponent,
    UserRegisterComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    HousingService,
    UserServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
