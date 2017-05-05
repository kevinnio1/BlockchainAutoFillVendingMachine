import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Http, Response} from "@angular/http";
import {XhrBaseRequestOptions, CookieUtils} from "../util/utils";

@Injectable()
export class AuthenticationService {
  public authenticated;
  private currentUsername: string;
  //private TOKEN_IDENTIFIER = "X-AUTH-TOKEN";
  private TOKEN_IDENTIFIER = "authorization";
  constructor(private http: Http, private cookieUtils: CookieUtils, private xhrBaseRequestOptions: XhrBaseRequestOptions) {
    this.checkAuthentication();
  }

  login(username: string, password: string): Observable<boolean> {

    return this.http.post('/api/login', JSON.stringify({ "username": username, "password": password }), this.xhrBaseRequestOptions)
      .map((response: Response) => {
        // login successful if there's a jwt token in the response
        let token = response.headers.get(this.TOKEN_IDENTIFIER);
        console.log("Token nu: ");
        console.log(token);
        if (token) {
          console.log("boventse if");
          this.authenticated = true;
          this.cookieUtils.createCookie(this.TOKEN_IDENTIFIER,token,100);
          this.setLocalStorageUsername(username);
          // return true to indicate successful login
          return true;
        } else {
          console.log("in de else");
          this.setLocalStorageUsername("");
          // return false to indicate failed login
          return false;

        }
      });
  }

  checkAuthentication(){
    this.authenticated = this.cookieUtils.getCookie(this.TOKEN_IDENTIFIER)!=null;
  }

  getToken(){
    return this.cookieUtils.getCookie(this.TOKEN_IDENTIFIER);
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post('/api/register', JSON.stringify({ "username": username, "password": password}), this.xhrBaseRequestOptions);

  }

  private setLocalStorageUsername(username: string): void {
    localStorage.setItem("username", username);
  }

  getCurrentUsername(): string {
    return localStorage.getItem("username") || "";
  }

  changePassword(currentPassword: string, newPassword: string): Observable<boolean> {
    return this.http.put("/user/changePassword", JSON.stringify({"currentPassword": currentPassword, "newPassword": newPassword}), this.xhrBaseRequestOptions)
      .map((response: Response) => {
        if (response.text() === "true") {
          return true;
        } else {
          return false
        }
      });
  }

  disableUser(password: string): Observable<boolean> {
    return this.http.put("/user/disable", password, this.xhrBaseRequestOptions)
      .map((response: Response) => {
      if (response.text() === "true") {
        return true;
      } else {
        return false
      }
    });
  }

  logout(): void {
    // clear cookie to log user out
    this.authenticated = false;
    this.cookieUtils.deleteCookie(this.TOKEN_IDENTIFIER);
    this.setLocalStorageUsername("");
  }
}
