import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Piatto} from '../../models/piatto/piatto.module';
import {PiattoService} from '../piatto.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-piatti-home',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './piatti-home.component.html',
  styleUrl: './piatti-home.component.css'
})
export class PiattiHomeComponent implements OnInit {
  piatti: Piatto[] = [];
  piatto: Piatto = {nome: "", ingredienti: ""};

  constructor(private _piattoService: PiattoService, private _router: Router, private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.onLoadPiatti();
  }

  private onLoadPiatti() {
    this._piattoService.getAllPiatti().subscribe(
      data => {
        console.log(data);
        this.piatti = data;
      },
      error => console.log(error)
    );
  }


  onSave() {
    this._piattoService.saveNewPiatto(this.piatto).subscribe(
      data => {
        console.log("Piatto creato: " + data);
        this._router.navigate([this._activatedRoute.snapshot.url + "/" + data.nome]).then(
          error => console.log(error));
      },
      error => console.log(error)
    );
  }

  onRemove(nomePiatto: string) {
    console.log("Piatto ricevuto: " + nomePiatto);
    this._piattoService.removePiatto(nomePiatto).subscribe(
      data => {
        console.log(data);
        this.onLoadPiatti();
      },
      error => console.log(error)
    );
  }
}
