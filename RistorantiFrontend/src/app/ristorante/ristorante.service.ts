import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Ristorante} from '../models/ristorante/ristorante.module';

@Injectable({
  providedIn: 'root'
})
export class RistoranteService {
  API_BASE_URL = 'api/ristorante/v1/';

  constructor(private http: HttpClient) {
  }

  getAllRistoranti(): Observable<Ristorante[]> {
    return this.http.get<Ristorante[]>(this.API_BASE_URL);
  }

  getRistoranteByName(nomeRistorante: string): Observable<Ristorante> {
    return this.http.get<Ristorante>(this.API_BASE_URL + nomeRistorante);
  }

  saveNewRistorante(ristorante: Ristorante): Observable<Ristorante> {
    return this.http.post<Ristorante>(this.API_BASE_URL + "addRistorante", ristorante);
  }

  updateRistorante(ristorante: Ristorante): Observable<Ristorante> {
    return this.http.post<Ristorante>(this.API_BASE_URL + "updateRistorante/" + ristorante.nome, ristorante);
  }

  removeRistorante(nomeRistorante: string) {
    return this.http.delete(this.API_BASE_URL + "removeRistorante/" + nomeRistorante);
  }
}
