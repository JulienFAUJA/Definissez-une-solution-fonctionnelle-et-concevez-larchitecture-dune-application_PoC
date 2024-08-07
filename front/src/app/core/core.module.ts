import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import * as fr from '@angular/common/locales/fr';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { HomeComponent } from '../pages/home/home.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderAnonymousComponent } from './components/header-anonymous/header-anonymous.component';
import { AuthInterceptor } from '../interceptors/auth.interceptor';
import { ChatComponent } from './components/chat/chat.component';
import { ImageLinkPipe } from 'app/core/pipes/image-link.pipe';
import { LoginComponent } from 'app/pages/login/login.component';
import { RegisterComponent } from 'app/pages/register/register.component';

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    HeaderAnonymousComponent,
    ChatComponent,
    ImageLinkPipe,
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
  exports: [
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    MaterialModule,
    ReactiveFormsModule,
    ImageLinkPipe,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'fr-FR' },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
})
export class CoreModule {
  constructor() {
    registerLocaleData(fr.default);
  }
}

