/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {CookieUtils, XhrBaseRequestOptions} from "../../util/utils";

@Component({
  selector: 'vendingmachine-component',
  templateUrl: './vendingmachine.component.html',
  styleUrls: ['./vendingmachine.component.css'],
  providers: [XhrBaseRequestOptions,CookieUtils]
})

export class VendingmachineComponent implements OnInit{
  private stock:number=0;
  private amount:number;
  private loadingRefill:boolean = true;
  private loadingBuyOne:boolean = false;
  constructor(private http:Http, private xhrBaseRequestOptions: XhrBaseRequestOptions,){}


  submitRefill(){


    if(this.amount>0){
      this.loadingRefill = true;
    var url = "/api/blockchain/stockRefill/" + this.amount
    console.log(url);

    this.http.post(url,{},this.xhrBaseRequestOptions).map(res =>res.json()).subscribe(
      result => {
        console.log(result);
        this.stock = result;
        this.loadingRefill = false;
      },
      error => {console.log(error as string);}

    );
    }else {
      console.log("niets ingevuld!");
    }
  }

  submitBuyOne() {
    this.loadingBuyOne = true;
    this.http.post("/api/blockchain/buyOne/",{},this.xhrBaseRequestOptions).map(res => res.json()).subscribe(
      result => {
        this.loadingBuyOne = false;
        this.stock = result;
      }
    );

  }


  ngOnInit(){
   this.http.get("/api/blockchain/getStock",this.xhrBaseRequestOptions).map(result => result.json()).subscribe(
      result => {
        console.log("Resultaat get Stock: ");
        console.log(result);
        this.stock = result;
        //console.log(this.stock);
        this.loadingRefill = false;
      },
      error =>  {console.log(error as string);}
    );
  }
}
