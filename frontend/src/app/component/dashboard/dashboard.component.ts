import {Component, OnInit, OnDestroy} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {AuthenticationService} from "../../service/authentication.service";
import {User} from "../../model/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'dashboard-component',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent{
  constructor(private authService: AuthenticationService, private router: Router) { }

logout(){
  this.authService.logout();
  this.router.navigate(['/login']);
}
}