import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Http, RequestOptions,Headers, Response} from "@angular/http";
import { SubscribeResultHandler, CookieUtils} from "../util/utils";

@Injectable()
export class BlockchainService {

  constructor(private http: Http,
              private cookieUtils:CookieUtils,
              private subscribeResultHandler: SubscribeResultHandler) {

  }



  public makeHeaderWithToken():RequestOptions{

    //let headers = new Headers({ 'X-AUTH-TOKEN': this.cookieUtils.getCookie('X-AUTH-TOKEN')});
    let options = new RequestOptions();
    options.headers = new Headers({ 'Authorization': this.cookieUtils.getCookie('Authorization'),'Content-Type' :'application/json','X-Requested-With': 'XMLHttpRequest'});
    return options;
  }

  public getStock(): Observable<number> {
    return this.http.get("/api/blockchain/getStock", this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public getPercentValueOfStock():Observable<number>{
    return this.http.get("/api/blockchain/getPercentStock", this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public getAccounts(): Observable<String[]> {
    return this.http.get("/api/blockchain/getAccounts", this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public submitRefill(amount:number ){
    var url = "/api/blockchain/stockRefill/" + amount;
    return this.http.post(url,{}, this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public submitMin(amount:number ){
    var url = "/api/blockchain/setMinStock/" + amount;
    return this.http.post(url,{}, this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }
  public submitMax(amount:number ){
    var url = "/api/blockchain/setMaxStock/" + amount;
    return this.http.post(url,{}, this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public buyOne(){
    var url = "/api/blockchain/buyOne/";
    return this.http.post(url,{}, this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }

  public getMaxStock(): Observable<number> {
    return this.http.get("/api/blockchain/getMaxStock", this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }
  public getMinStock(): Observable<number> {
    return this.http.get("/api/blockchain/getMinStock", this.makeHeaderWithToken())
      .map(this.subscribeResultHandler.handleResponse)
      .catch(this.subscribeResultHandler.handleError);
  }
}
