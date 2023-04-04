import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { Observable, of } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


loginData={
  username:'',
  password:''
}

// hide: any;
  // loginStatusSubject: any;

constructor(private login:LoginService, private snack: MatSnackBar, private router: Router) { }

ngOnInit(): void {
}

formSubmit() 
{
  // console.log("form is submitted");
  if(this.loginData.username.trim()== '' || this.loginData.username== null) 
  {
    this.snack.open('Username Required !! ', '', {
      duration: 3000,
    });
    return;
  }
  if(this.loginData.password.trim()== '' || this.loginData.password== null) 
  {
    this.snack.open('Password Required !! ', '', {
      duration: 3000,
    });
    return;
  }
    // Token generate
    this.login.generateToken(this.loginData).subscribe (
      (data: any)=>{
        //sucsess
        console.log('Success');
        console.log(data);
        
        this.login.loginUser(data.token)

      
        //login

        this.login.getCurrentUser().subscribe(
          (user: any)=> {
            this.login.setUser(user);
            console.log(user);

            // redirect to dashboard
          this.router.navigate(['dashboard']); 
          this.login.loginStatusSubject.next(true)
          }
        )
                
      },
      (error)=>{
        //error
        console.log('Error !');
        console.log(error);
        this.snack.open('Invalid Details !! Try again', '', {
          duration: 3000,
        }
        );
        
      }
    );

  }
}

