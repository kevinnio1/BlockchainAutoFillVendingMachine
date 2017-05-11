import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { PeerComponent} from './component/peer/peer.component'
import {VendingmachineComponent} from './component/vendingmachine/vendingmachine.component'
import { NgSemanticModule } from "ng-semantic";
import{ LoginComponent } from './component/login/login.component'
import{ RegisterComponent } from './component/register/register.component'
import {AuthGuard} from "./guards/auth.guard";
import {AppRoutingModule} from '../app/app-routing.modules';
import {CookieUtils, SubscribeResultHandler} from "./util/utils";
import {AuthenticationService} from "./service/authentication.service";
import {DashboardComponent} from "./component/dashboard/dashboard.component"
import {BlockchainService} from "./service/blockchain.service";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    PeerComponent,
    LoginComponent,
    RegisterComponent,
    VendingmachineComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgSemanticModule
  ],
  providers: [AuthGuard,CookieUtils,AuthenticationService,BlockchainService,SubscribeResultHandler],
  bootstrap: [AppComponent]
})
export class AppModule { }
