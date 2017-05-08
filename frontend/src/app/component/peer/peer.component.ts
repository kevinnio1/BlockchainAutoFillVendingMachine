/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {CookieUtils} from "../../util/utils";
import {BlockchainService} from "../../service/blockchain.service";

@Component({
  selector: 'peer-component',
  templateUrl: './peer.component.html',
  styleUrls: ['./peer.component.css'],
  providers: [CookieUtils]
})

export class PeerComponent implements OnInit{
  private accounts:String[]=[];
  constructor(private http:Http, private blockchainService: BlockchainService){}



  ngOnInit(){
    this.blockchainService.getAccounts().subscribe(
      result => {
        //console.log(result);
        this.accounts = result;
        console.log(this.accounts);
      },
      error =>  {console.log(error as string);}
    );
  }
}
