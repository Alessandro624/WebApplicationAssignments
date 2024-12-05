import {Sender} from './sender.module';

export interface Message {
  sender: Sender;
  text: string;
}
