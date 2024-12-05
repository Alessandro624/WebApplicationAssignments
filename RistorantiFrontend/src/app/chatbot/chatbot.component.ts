import {Component, ElementRef, ViewChild} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Message} from '../models/message/message.module';
import {Sender} from '../models/message/sender.module';
import {ChatbotService} from './chatbot.service';

@Component({
  selector: 'app-chatbot',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './chatbot.component.html',
  styleUrl: './chatbot.component.css'
})
export class ChatbotComponent {
  protected readonly Sender = Sender;
  messages: Message[] = [];
  newMessage: string = "";
  isBotResponding: boolean = false;

  @ViewChild('chatBody') chatBody!: ElementRef;
  @ViewChild('messageInput') messageInput!: ElementRef;

  constructor(private _chatbotService: ChatbotService) {
  }

  sendMessage() {
    if (this.newMessage.trim()) {
      let message = this.newMessage;
      this.newMessage = "";
      this.isBotResponding = true;
      this.addMessageToChat(message, Sender.USER);
      this._chatbotService.sendMessage(message).subscribe({
          next: (res) => {
            this.addMessageToChat(res.choices[0].message.content, Sender.CHATBOT);
            this.isBotResponding = false;
          },
          error: (err) => {
            this.addMessageToChat("Errore durante la richiesta", Sender.CHATBOT);
            console.error('Errore durante la richiesta:', err);
            this.isBotResponding = false;
          }
        }
      );
    }
  }

  scrollToBottom() {
    setTimeout(() => {
      if (this.chatBody) {
        this.chatBody.nativeElement.scrollTop = this.chatBody.nativeElement.scrollHeight;
      }
    }, 0);
  }

  focusInput() {
    setTimeout(() => {
      if (this.messageInput) {
        this.messageInput.nativeElement.focus();
      }
    }, 0);
  }

  private addMessageToChat(message: string, sender: Sender) {
    this.messages.push({text: message, sender: sender});
    this.scrollToBottom();
    this.focusInput();
  }
}
