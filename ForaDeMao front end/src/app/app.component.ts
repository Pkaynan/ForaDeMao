import { Component } from '@angular/core';
import { MapaComponent } from './components/mapa/mapa.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MapaComponent],
  template: `<app-mapa></app-mapa>`
})
export class AppComponent {}
