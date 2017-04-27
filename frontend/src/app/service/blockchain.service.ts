import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {SubscribeResultHandler} from "../util/utils";
/**
 * Created by KeLe on 27/04/2017.
 */


@Injectable()
export class BlockchainService {
  constructor( private http:Http,private subscribeResultHandler: SubscribeResultHandler){}

  /*public getPeers () : Observable<any>
  {
    return this.http.get("/api/getPeersOfNode").map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }*/
}
