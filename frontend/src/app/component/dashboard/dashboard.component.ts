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
export class DashboardComponent implements OnInit{
  public isAdmin:boolean=false;
  constructor(private authService: AuthenticationService, private router: Router) { }



  ngOnInit()
  {
    this.authService.isAdmin().subscribe(
      result => {
        if(result == true){
          this.isAdmin = true;
        }else {
          this.isAdmin = false;
        }
      },
      error => {
        console.log(error as string);
      }
    );
  }


logout(){
  this.authService.logout();
  this.router.navigate(['/login']);
}


}
