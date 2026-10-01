// Espelham exatamente os records do backend:
// AcidenteMapaResponse, RodoviaMapaResponse, PontoPerigosoMapaResponse

export type Gravidade = 'LEVE' | 'GRAVE' | 'FATAL';
export type TipoAcidente = 'COLISAO' | 'TOMBAMENTO' | 'ATROPELAMENTO';
export type NivelRisco = 'GRANDE' | 'MODERADO' | 'BAIXO';


export interface AcidenteMapaResponse {
  id: number;
  latitude: number;
  longitude: number;
  tipo: TipoAcidente;
  gravidade: Gravidade;
  fatais: number;
  feridos: number;
  ilesos: number;
  dataHora: string;
  idRodovia: number;
  nomeRodovia: string;
  clima: string;
}

export interface CoordenadaResponse {
  latitude: number;
  longitude: number;
}

export interface RodoviaMapaResponse {
  id: number;
  nome: string;
  tipo: string;
  estado: string;
  tracado: CoordenadaResponse[];
  km: number;
}

export interface PontoPerigosoMapaResponse {
  id: number;
  latitude: number;
  longitude: number;
  nivelRisco: NivelRisco;
  idRodovia: number;
  nomeRodovia: string;
}

export interface BoundingBox {
  minLat: number;
  maxLat: number;
  minLon: number;
  maxLon: number;
}
