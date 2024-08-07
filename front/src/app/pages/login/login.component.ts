import { Component, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil, tap } from 'rxjs';
import { LoginRequest } from 'app/core/interfaces/loginRequest.interface';
import { AuthService } from 'app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit{
  public loginForm!:FormGroup;
  private destroy$: Subject<boolean> = new Subject();
  errorStr: string = '';



  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router ) {}

  ngOnInit():void{
    
    this.loginForm = this.fb.group({
     
      email:['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      
    });

  }

  onSubmitForm():void {
    if (this.loginForm.valid) {  // Vérifie si le formulaire est valide
    this.destroy$ = new Subject<boolean>();
    const loginRequest = this.loginForm.value as LoginRequest;
    
    this.authService.login(loginRequest)
    .pipe(
      takeUntil(this.destroy$))
    
    .subscribe({
      next: (response) => {
        tap((response) => console.log('[LoginComponent] Login response: ', response)),
          localStorage.setItem('token', response.token);
          this.router.navigate(['chat']);
      },
      error: (error) => {
        this.errorStr =
          error.error || '..................Une erreur est survenue lors de la connexion.',
          console.log("erreur",this.errorStr);
      },
    });
    console.log(this.errorStr);
  }
    
    
}
ngOnDestroy(): void {
  this.destroy$.next(true);
  this.destroy$.complete();
}
}
