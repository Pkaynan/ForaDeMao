import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {
  AcidenteMapaResponse,
  BoundingBox,
  PontoPerigosoMapaResponse,
  RodoviaMapaResponse
} from '../models/mapa.models';

@Injectable({ providedIn: 'root' })
export class MapaService {

  private readonly baseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  buscarAcidentes(bbox?: BoundingBox): Observable<AcidenteMapaResponse[]> {
    return this.http.get<AcidenteMapaResponse[]>(
      `${this.baseUrl}/acidentes`,
      { params: this.paramsDaArea(bbox) }
    );
  }

  buscarRodovias(): Observable<RodoviaMapaResponse[]> {
    return this.http.get<RodoviaMapaResponse[]>(`${this.baseUrl}/rodovias`);
  }

  buscarPontosPerigosos(bbox?: BoundingBox): Observable<PontoPerigosoMapaResponse[]> {
    return this.http.get<PontoPerigosoMapaResponse[]>(
      `${this.baseUrl}/pontos-perigosos`,
      { params: this.paramsDaArea(bbox) }
    );
  }

  // Bounding box do mapa: evita pedir a base inteira quando o usuário
  // está com zoom numa única cidade (ver MapaController no backend).
  private paramsDaArea(bbox?: BoundingBox): HttpParams {
    let params = new HttpParams();
    if (!bbox) return params;
    params = params
      .set('minLat', bbox.minLat)
      .set('maxLat', bbox.maxLat)
      .set('minLon', bbox.minLon)
      .set('maxLon', bbox.maxLon);
    return params;
  }
}
