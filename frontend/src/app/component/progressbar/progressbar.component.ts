/**
 * Created by KeLe on 28/04/2017.
 */

import {Component, Input, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {CookieUtils} from "../../util/utils";
import {BlockchainService} from "../../service/blockchain.service";

@Component({
  selector: 'progressbar-component',
  templateUrl: './progressbar.component.html',
  styleUrls: ['./progressbar.component.css'],
  providers: [CookieUtils]
})

export class ProgressbarComponent implements OnInit{
  constructor(){}

  getPercentValue(){
    return 80;
  }

  ngOnInit(){

  }
}
