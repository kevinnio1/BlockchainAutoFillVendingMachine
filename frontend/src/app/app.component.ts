import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {Peer} from "./model/peer";
import "rxjs/add/operator/map";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  peers: Peer[]=[];



  constructor(private http:Http){}

  ngOnInit(){
    this.http.get("/api/blockchain/getPeersOfNode").map(result => result.json()["peers"]).subscribe(
      result => { for(var key in result){
                    var value = result[key];
                    console.log(value);
                    this.peers.push(value);
                  }
    },
      error =>  {console.log(error as string);}
    );
  }




}
