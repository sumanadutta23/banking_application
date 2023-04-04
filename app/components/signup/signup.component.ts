import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatSelectModule} from '@angular/material/select';
import { UserService } from "src/app/services/UserService";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService, private snack:MatSnackBar) { }

  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a valid email';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  // loginData={
  //   username:'',
  //   email:'',
  //   phone:'',
  //   password:'',
  //   firstname:'',
  //   lastname:''

  // }
  public user = {
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:'',    
  }

  
  ngOnInit(): void {
    
  }

  // onSubmit() 
  // {
    
  //   if((this.loginData.username!= '' && this.loginData.password!='' && this.loginData.email!= '' && this.loginData.phone!= '') && (this.loginData.username!=null && this.loginData.password!=null && this.loginData.email!=null && this.loginData.phone!=null))
  //   {
  //     console.log("We have to submit the form to server");
  //   }else{
  //     console.log("fields are empty !!")
  //   }
  // }

  formSubmit() {
    console.log(this.user);
      if (this.user.username == '' || this.user.username == null || this.user.password == '' || this.user.password == null || this.user.email == '' || this.user.email == null || this.user.phone == '' || this.user.phone == null || this.user.firstName == '' || this.user.firstName == null || this.user.lastName == '' || this.user.lastName == null) {
        // alert('user is required');
        this.snack.open('Username is required !! ', '', {
          duration: 3000,
        })
        return;
      }
      // addUser: userservice
      this.userService.addUser(this.user).subscribe(
        (data: any)=>{
          // success
          console.log(data);
          // alert('success');
          Swal.fire('Success done !!', '', 'success');
          // window.location.reload();

          
        },
        (error) => {
          console.log(error);
          // alert('Something went wrong');
          this.snack.open('Something went wrong !!', '',{
            duration:3000,
          })
        }
      )
  }

}
