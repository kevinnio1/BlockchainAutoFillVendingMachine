import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Http, Response} from "@angular/http";
import {XhrBaseRequestOptions, SubscribeResultHandler} from "../util/utils";

@Injectable()
export class BlockchainService {

  constructor(private http: Http,
              private xhrBaseRequestOptions: XhrBaseRequestOptions,
              private subscribeResultHandler: SubscribeResultHandler) {

  }
  public getStock(): Observable<number> {
    return this.http.get("/api/blockchain/getStock", this.xhrBaseRequestOptions)
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

}
