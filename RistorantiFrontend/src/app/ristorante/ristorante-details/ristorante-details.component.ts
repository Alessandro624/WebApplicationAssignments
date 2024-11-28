import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Ristorante} from '../../models/ristorante/ristorante.module';
import {RistoranteService} from '../ristorante.service';
import {FormsModule} from '@angular/forms';
import {LoadingComponent} from '../../common/loading/loading.component';

@Component({
  selector: 'app-ristorante-details',
  standalone: true,
  imports: [
    FormsModule,
    LoadingComponent
  ],
  templateUrl: './ristorante-details.component.html',
  styleUrl: './ristorante-details.component.css'
})
export class RistoranteDetailsComponent implements OnInit {
  ristorante!: Ristorante;
  loading = false;

  constructor(private _ristoranteService: RistoranteService, private _router: Router, private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loading = true;
    const nomeRistorante = this._activatedRoute.snapshot.paramMap.get('nome');
    console.log("Nome ristorante: " + nomeRistorante);
    if (nomeRistorante == null) {
      throw new Error("Nome ristorante non trovato");
    }
    this._ristoranteService.getRistoranteByName(nomeRistorante).subscribe(
      (data: Ristorante) => {
        this.ristorante = data;
        console.log("Ristorante ricevuto.");
        this.loading = false;
      },
      (error: any) => {
        console.log(error);
        this.loading = false;
      }
    );
  }

  onUpdate(): void {
    console.log("Ristorante modificato: " + this.ristorante);
    this._ristoranteService.updateRistorante(this.ristorante).subscribe(
      (data: Ristorante) => this.ristorante = data,
      (error: any) => console.log(error)
    );
  }
}
