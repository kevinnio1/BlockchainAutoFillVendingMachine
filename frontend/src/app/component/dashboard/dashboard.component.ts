import {Component, OnInit, OnDestroy} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {AuthenticationService} from "../../service/authentication.service";
import {User} from "../../model/user/user";
import {Router} from "@angular/router";
import {BlockchainService} from "../../service/blockchain.service";

@Component({
  selector: 'dashboard-component',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  public isAdmin:boolean=false;
  public username:string;
  public balance:number;
  constructor(private authService: AuthenticationService, private router: Router, private blockchainService:BlockchainService) {
  }



  onUpdateBalance(emp){
    this.blockchainService.getBalanceCurrUser().subscribe(
      result=>{
        this.balance=result;
      },
      error => {
        console.log(error as string);
      }
    );
  }

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
    this.username= this.authService.getCurrentUsername();
    this.onUpdateBalance(null);

  }


logout(){
  this.authService.logout();
  this.router.navigate(['/login']);
}


}
