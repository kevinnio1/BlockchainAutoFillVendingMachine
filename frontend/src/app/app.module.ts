import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import  {BlockchainService} from './service/blockchain.service'
import { L_SEMANTIC_UI_MODULE } from 'angular2-semantic-ui';
import { PeerComponent} from './component/peer/peer.component'


@NgModule({
  declarations: [
    AppComponent,
    PeerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    L_SEMANTIC_UI_MODULE
  ],
  providers: [BlockchainService],
  bootstrap: [AppComponent]
})
export class AppModule { }
