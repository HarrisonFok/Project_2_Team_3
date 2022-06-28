import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ButtonComponent } from './components/button/button.component';
import { SearchbarComponent } from './components/searchbar/searchbar.component';
import { MapComponent } from './map/map.component';
import { FormsModule } from '@angular/forms';
import { PlacesComponent } from './components/places/places.component';
import { PlaceItemComponent } from './components/place-item/place-item.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ButtonComponent,
    SearchbarComponent,
    MapComponent,
    PlacesComponent,
    PlaceItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
