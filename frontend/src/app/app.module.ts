import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { PeerComponent} from './component/peer/peer.component'
import {VendingmachineComponent} from './component/vendingmachine/vendingmachine.component'
import { NgSemanticModule } from "ng-semantic";


@NgModule({
  declarations: [
    AppComponent,
    PeerComponent,
    VendingmachineComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgSemanticModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
