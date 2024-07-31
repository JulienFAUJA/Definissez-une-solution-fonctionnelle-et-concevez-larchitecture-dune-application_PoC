// chat.service.ts

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { interval, Observable, startWith, switchMap, tap } from 'rxjs';
import { Chat } from '../models/chat.model';
import { AuthService } from './auth.service';
import { Question } from '../models/question.model';
import { QuestionRequest } from '../interfaces/questionRequest.interface';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private pathService = 'http://localhost:8080/api/questions';

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    if (!token) {
      this.authService.logOut();
    }
    return new HttpHeaders({ Authorization: `Bearer ${token}` });
  }

  public all(): Observable<Question[]> {
    return this.httpClient
      .get<Question[]>(`${this.pathService}`, { headers: this.getHeaders() });
      //.pipe(tap((response) => console.log('Get response: ', response)));
  }

  public create(question: QuestionRequest): Observable<Question> {
    return this.httpClient.post<Question>(
      `${this.pathService}`,
      question,
      {
        headers: this.getHeaders(),
      }
    );
  }

  public pollQuestions(): Observable<Question[]> {
    return interval(1000).pipe( // poll every 5 seconds
      startWith(0), 
      switchMap(() => this.all())
    );
  }
  
}


