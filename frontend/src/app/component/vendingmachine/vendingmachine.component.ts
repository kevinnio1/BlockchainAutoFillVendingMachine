/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {CookieUtils, SubscribeResultHandler} from "../../util/utils";
import {BlockchainService} from "../../service/blockchain.service";

@Component({
  selector: 'vendingmachine-component',
  templateUrl: './vendingmachine.component.html',
  styleUrls: ['./vendingmachine.component.css'],
  providers: [CookieUtils,BlockchainService,SubscribeResultHandler]
})

export class VendingmachineComponent implements OnInit{
  private stock:number=0;
  private amount:number;
  private loadingRefill:boolean = true;
  private loadingBuyOne:boolean = false;
  constructor(private http:Http, private blockchainService: BlockchainService){}


  submitRefill(){


    if(this.amount>0){
      this.loadingRefill = true;

    this.blockchainService.submitRefill(this.amount).subscribe(
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
    this.blockchainService.buyOne().subscribe(
      result => {
        this.loadingBuyOne = false;
        this.stock = result;
      }
    );

  }


  ngOnInit(){
   this.blockchainService.getStock().subscribe(
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
