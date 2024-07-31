// chat.component.ts
import {
  Component,
  OnDestroy,
  OnInit,
  AfterViewChecked,
  ViewChild,
  ElementRef,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import {
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { Chat } from 'app/core/models/chat.model';
import { Question } from 'app/core/models/question.model';
import { ChatService } from 'app/core/services/chat.service';
import { QuestionRequest } from '../../interfaces/questionRequest.interface';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit, OnDestroy, AfterViewChecked {
  chat!: Chat;
  questions: Question[] = [];
  public chatForm!: FormGroup;
  public currentLength: number = 0;
  public maxLength: number = 255;
  private destroy$: Subject<boolean> = new Subject();
  error_str!: string;
  user1Email!: string;
  user2Email!: string;

  @ViewChild('chatFrame') private chatFrame!: ElementRef;
  @ViewChild('notificationAudio') private notificationAudio!: ElementRef;

  constructor(
    private route: ActivatedRoute,
    private chatService: ChatService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadChat();
    this.pollForNewQuestions();
    console.log('questions:', this.questions);
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  loadChat(): void {
    this.chatForm = this.fb.group({
      questionTexte: [
        '',
        [Validators.required, Validators.maxLength(this.maxLength)],
      ],
    });

    this.chatForm.get('questionTexte')?.valueChanges.subscribe((value) => {
      this.currentLength = value.length;
    });

    this.chatService
      .all()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (questions: Question[]) => {
          if (questions.length > 0) {
            this.user1Email = questions[0].utilisateurEmail;
            this.user2Email =
              questions.find((q) => q.utilisateurEmail !== this.user1Email)
                ?.utilisateurEmail || '';
          }
          console.log('Questions received:', questions); // Ajoutez ce log pour vérifier les données
          this.questions = questions;
        },
        error: (error) => {
          console.error(
            'Une erreur est survenue lors du chargement des questions',
            error
          );
        },
      });
  }

  pollForNewQuestions(): void {
    interval(1000)
      .pipe(
        switchMap(() => this.chatService.all()),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (questions: Question[]) => {
          console.log('Polled questions:', questions);
          this.questions = questions;
          console.log('Updated questions:', this.questions);
        },
        error: (error) => {
          console.error(
            'Une erreur est survenue lors de la récupération des questions',
            error
          );
        },
      });
  }

  onInputChange(): void {
    const questionTexteControl = this.chatForm.get('questionTexte');
    if (questionTexteControl) {
      this.currentLength = questionTexteControl.value.length;
    }
  }

  onSubmitForm(): void {
    if (this.chatForm.valid) {
      const questionRequest = this.chatForm.value as QuestionRequest;
      this.chatService
        .create(questionRequest)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (question) => {
            console.log(question);
            this.questions.push(question); // Ajouter la nouvelle question au début de la liste
            this.playNotificationSound();
            this.scrollToBottom();
            this.chatForm.reset(); // Réinitialiser le formulaire
            this.currentLength = 0;
          },
          error: (error) => {
            console.error(
              "Une erreur est survenue lors de l'envoi de la question",
              error
            );
          },
        });
    }
  }

  private playNotificationSound(): void {
    try {
      this.notificationAudio.nativeElement.play();
    } catch (err) {
      console.error('Failed to play notification sound', err);
    }
  }

  private scrollToBottom(): void {
    try {
      this.chatFrame.nativeElement.scrollTop =
        this.chatFrame.nativeElement.scrollHeight;
    } catch (err) {
      console.error(err);
    }
  }

  onSubmitForm2(): void {
    if (this.chatForm.valid) {
      // Vérifie si le formulaire est valide
      this.destroy$ = new Subject<boolean>();
      const questionRequest = this.chatForm.value as QuestionRequest;
      console.log('questionRequest', questionRequest);
      this.chatService
        .create(questionRequest)
        .pipe(takeUntil(this.destroy$))

        .subscribe({
          next: (response) => {
            this.router.navigate(['chat']);
          },
          error: (error) => {
            (this.error_str =
              error.error ||
              '..................Une erreur est survenue lors de la connexion.'),
              console.log('erreur', this.error_str);
          },
        });
      console.log(this.error_str);
    }
  }
}
