/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";

@Component({
  selector: 'peer-component',
  templateUrl: './peer.component.html',
  styleUrls: ['./peer.component.css']
})

export class PeerComponent implements OnInit{
  private accounts:String[]=[];
  constructor(private http:Http){}



  ngOnInit(){
    this.http.get("/api/blockchain/getAccounts").map(result => result.json()).subscribe(
      result => {
        //console.log(result);
        this.accounts = result;
        console.log(this.accounts);
      },
      error =>  {console.log(error as string);}
    );
  }
}
