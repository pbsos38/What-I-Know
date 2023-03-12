import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { User } from 'src/app/model/user';
import { UserServiceService } from 'src/app/Service/user-service.service';
import { AlertyfyService } from 'src/app/Service/alertyfy.service';
import { AuthService } from 'src/app/Service/auth.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})

export class UserRegisterComponent implements OnInit {
  registrationForm:FormGroup;
  user:User
  userSubmitted:boolean;
  constructor(private fb: FormBuilder,private userService: AuthService, private alertyfyservice: AlertyfyService) { }

  ngOnInit() {
    // this.registrationForm = new FormGroup({
    //   userName: new FormControl('Mark', Validators.required),
    //   email: new FormControl(null,[Validators.required,Validators.email]),
    //   password: new FormControl(null,[Validators.required,Validators.minLength(8)]),
    //   confirmPassword: new FormControl(null,[Validators.required]),
    //   mobile: new FormControl(null,[Validators.required,Validators.maxLength(10)])

    // },this.passwordMatchValidator);

    this.createUserRegitrationForm();
    this.registrationForm.controls['email'].setValue("email@domain.com");
  }

  createUserRegitrationForm(){
    this.registrationForm = this.fb.group({
      userName: [null, Validators.required],
      email: [null,[Validators.required,Validators.email]],
      password: [null,[Validators.required,Validators.minLength(8)]],
      confirmPassword: [null,[Validators.required]],
      mobile: [null,[Validators.required,Validators.maxLength(10)]]

    },{validators: this.passwordMatchValidator});

    this.registrationForm.controls['email'].setValue("email@domain.com");

  }

  passwordMatchValidator(ngF: AbstractControl) : ValidationErrors | null{
    return ngF.get('password')?.value === ngF.get('confirmPassword')?.value ? null :
    {notmatched: true};
  }
  onSubmit(){
    console.log(this.registrationForm);
    this.userSubmitted = true;
    if(this.registrationForm.valid){
      this.user =  Object.assign(this.registrationForm.value);
      this.userService.registerUser(this.user).subscribe(
        ()=>{
          this.registrationForm.reset();
          this.userSubmitted = false;
          this.alertyfyservice.success("Congrats! you are registered now.")

        },error=>{
          console.log(error);
          this.alertyfyservice.error(error.error);
        }
      );
    }else{
      this.alertyfyservice.error("Some Error Found!");

    }

  }

  userData(): User{
    return this.user = {
      userName:this.userName.value,
      email:this.email.value,
      password:this.password.value,
      mobile:this.mobile.value
    }
  }


  get userName(){
    return this.registrationForm.get('userName') as FormControl;
  }
  get email(){
    return this.registrationForm.get('email') as FormControl;
  }
  get password(){
    return this.registrationForm.get('password') as FormControl;
  }
  get confirmPassword(){
    return this.registrationForm.get('confirmPassword') as FormControl;
  }
  get mobile(){
    return this.registrationForm.get('mobile') as FormControl;
  }


}
