import {Sender} from './sender.model';

export interface Message {
  sender: Sender;
  text: string;
}
