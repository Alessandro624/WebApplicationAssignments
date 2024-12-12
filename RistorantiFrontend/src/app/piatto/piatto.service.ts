import {Injectable} from '@angular/core';
import {Piatto} from '../models/piatto/piatto.model';
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PiattoService {

  API_BASE_URL = 'api/piatto/v1/';

  constructor(private http: HttpClient) {
  }

  getAllPiatti(): Observable<Piatto[]> {
    return this.http.get<Piatto[]>(this.API_BASE_URL);
  }

  getPiattoByName(nomePiatto: string): Observable<Piatto> {
    return this.http.get<Piatto>(this.API_BASE_URL + nomePiatto);
  }

  saveNewPiatto(piatto: Piatto): Observable<Piatto> {
    return this.http.post<Piatto>(this.API_BASE_URL + "addPiatto", piatto);
  }

  updatePiatto(piatto: Piatto): Observable<Piatto> {
    return this.http.post<Piatto>(this.API_BASE_URL + "updatePiatto/" + piatto.nome, piatto);
  }

  removePiatto(nomePiatto: string) {
    return this.http.delete(this.API_BASE_URL + "removePiatto/" + nomePiatto);
  }
}
