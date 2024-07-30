// question.model.ts
export class Question {
  constructor(
    public questionId: number,
    public questionTexte: string,
    public utilisateurEmail: string,
    public chatId: number
  ) {}
}
