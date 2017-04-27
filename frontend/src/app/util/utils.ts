import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
/**
 * Created by KeLe on 27/04/2017.
 */

@Injectable()
export class SubscribeResultHandler {
  handleResponse(res: Response) {
    let body;
    if (res.text()){ // workaround for empty responses
      body = res.json();
    }
    return body || {};
  }

  handleError(error: any) {
    let errMsg: string = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    return Observable.throw(errMsg);
  }
}
