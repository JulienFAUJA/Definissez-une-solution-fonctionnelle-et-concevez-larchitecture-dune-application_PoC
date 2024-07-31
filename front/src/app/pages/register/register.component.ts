import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { RegisterRequest } from 'app/core/interfaces/registerRequest.interface';
import { AuthService } from 'app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    date_naissance: new FormControl(''),
    password: new FormControl(''),
  });
  private destroy$: Subject<boolean> = new Subject();
  errorStr: string = '';

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit():void{
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;
    this.registerForm = this.fb.group({
     
      email:['', [Validators.required, Validators.email]],
      firstname:['', [Validators.required]],
      lastname:['', [Validators.required]],
      date_naissance:['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(passwordPattern)]],
      
    });
  }

  
  onSubmitForm():void {
    if (this.registerForm.valid) {  // Vérifie si le formulaire est valide
    this.destroy$ = new Subject<boolean>();
    const registerRequest = this.registerForm.value as RegisterRequest;
    this.authService.register(registerRequest)
    .pipe(
      takeUntil(this.destroy$))
    
    .subscribe({
      next: (response) => {
          localStorage.setItem('token', response.token);
          this.router.navigate(['/chat']);
      },
      error: (error) => {
        this.errorStr =
        error.error || '..................Une erreur est survenue lors de la connexion.';
      },
    });
    
  }
  else{
    console.log("erreur:", this.errorStr);
  }
    
    
}
ngOnDestroy(): void {
  this.destroy$.next(true);
  this.destroy$.complete();
}


}