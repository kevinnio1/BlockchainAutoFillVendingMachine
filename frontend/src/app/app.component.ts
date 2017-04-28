import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {Peer} from "./model/peer";
import "rxjs/add/operator/map";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private http:Http){}
  title = 'app works!';
  private peers: Peer[]=[];

  ngOnInit(){
    this.http.get("/api/blockchain/getPeersOfNode").map(result => result.json()).subscribe(
      result => {this.peers = result.map(item => {return new Peer(item.address,item.pkiID,item.type)}); console.log(this.peers);},
      error =>  {console.log(error as string);}
    );
  }




}
