import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { ContactComponent } from './contact/contact.component';
import { PlannerComponent } from './planner/planner.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ContactComponent,
    PlannerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
