/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {CookieUtils, XhrBaseRequestOptions} from "../../util/utils";

@Component({
  selector: 'peer-component',
  templateUrl: './peer.component.html',
  styleUrls: ['./peer.component.css'],
  providers: [XhrBaseRequestOptions,CookieUtils]
})

export class PeerComponent implements OnInit{
  private accounts:String[]=[];
  constructor(private http:Http, private xhrBaseRequestOptions: XhrBaseRequestOptions,){}



  ngOnInit(){
    this.http.get("/api/blockchain/getAccounts",this.xhrBaseRequestOptions).map(result => result.json()).subscribe(
      result => {
        //console.log(result);
        this.accounts = result;
        console.log(this.accounts);
      },
      error =>  {console.log(error as string);}
    );
  }
}
