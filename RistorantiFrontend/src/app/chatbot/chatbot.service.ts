import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {
  private apiUrl = 'https://api.openai.com/v1/chat/completions';
  private apiKey = environment.openAIKey;

  constructor(private http: HttpClient) {
  }

  sendMessage(message: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${this.apiKey}`
    });
    const body = {
      model: "gpt-3.5-turbo",
      messages: [
        {
          role: "system",
          content: "Sei un assistente utile che fornisce aiuto a clienti di alcuni ristoranti italiani."
        },
        {role: "user", content: message}
      ]
    };
    return this.http.post(this.apiUrl, body, {headers});
  }
}
