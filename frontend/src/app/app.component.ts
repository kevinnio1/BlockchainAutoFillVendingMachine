import {Component, OnInit} from '@angular/core';
import {Http, Response} from "@angular/http";
import {removeSummaryDuplicates} from "@angular/compiler";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private http:Http){}
  title = 'app works!';
  private nodes : Observable<Response>;
  private proberen: string='';

  ngOnInit(){
   this.nodes =  this.http.get("/api/blockchain/getPeersOfNode");
   console.log(this.nodes);
   this.nodes.subscribe(
     result =>   {this.proberen = result.toString();}
   );
  }




}
