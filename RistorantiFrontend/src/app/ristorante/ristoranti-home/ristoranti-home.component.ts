import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {Ristorante} from '../../models/ristorante/ristorante.model';
import {RistoranteService} from '../ristorante.service';

@Component({
  selector: 'app-ristoranti-home',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule
  ],
  templateUrl: './ristoranti-home.component.html',
  styleUrl: './ristoranti-home.component.css'
})
export class RistorantiHomeComponent implements OnInit {
  ristoranti: Ristorante[] = [];
  ristorante: Ristorante = {nome: "", descrizione: "", ubicazione: ""};

  constructor(private _ristoranteService: RistoranteService, private _router: Router, private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.onLoadRistoranti();
  }

  private onLoadRistoranti() {
    this._ristoranteService.getAllRistoranti().subscribe(
      data => {
        console.log("Ristoranti caricati: " + data);
        this.ristoranti = data;
      },
      error => console.log(error)
    );
  }


  onSave() {
    this._ristoranteService.saveNewRistorante(this.ristorante).subscribe(
      data => {
        console.log("Ristorante creato: " + data);
        this._router.navigate([this._activatedRoute.snapshot.url + "/" + data.nome]).then(
          error => console.log(error));
      },
      error => console.log(error)
    );
  }

  onRemove(nomeRistorante: string) {
    console.log("Ristorante ricevuto: " + nomeRistorante);
    this._ristoranteService.removeRistorante(nomeRistorante).subscribe(
      data => {
        console.log(data);
        this.onLoadRistoranti();
      },
      error => console.log(error)
    );
  }
}
