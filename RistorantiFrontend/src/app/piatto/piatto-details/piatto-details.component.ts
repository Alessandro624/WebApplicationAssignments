import {Component, OnInit} from '@angular/core';
import {LoadingComponent} from '../../common/loading/loading.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Piatto} from '../../models/piatto/piatto.module';
import {ActivatedRoute} from '@angular/router';
import {PiattoService} from '../piatto.service';

@Component({
  selector: 'app-piatto-details',
  standalone: true,
  imports: [
    LoadingComponent,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './piatto-details.component.html',
  styleUrl: './piatto-details.component.css'
})
export class PiattoDetailsComponent implements OnInit {
  piatto!: Piatto;
  loading = false;

  constructor(private _piattoService: PiattoService, private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loading = true;
    const nomePiatto = this._activatedRoute.snapshot.paramMap.get('nome');
    console.log("Nome piatto: " + nomePiatto);
    if (nomePiatto == null) {
      throw new Error("Nome piatto non trovato");
    }
    this._piattoService.getPiattoByName(nomePiatto).subscribe(
      (data: Piatto) => {
        this.piatto = data;
        console.log("Piatto ricevuto.");
        this.loading = false;
      },
      (error: any) => {
        console.log(error);
        this.loading = false;
      }
    );
  }

  onUpdate(): void {
    console.log("Piatto modificato: " + this.piatto);
    this._piattoService.updatePiatto(this.piatto).subscribe(
      (data: Piatto) => this.piatto = data,
      (error: any) => console.log(error)
    );
  }
}
