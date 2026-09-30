import { AfterViewInit, Component, OnDestroy } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import * as Leaf from "leaflet";
import { MapaService } from "../../services/mapa.service";
import {
  AcidenteMapaResponse,
  Gravidade,
  PontoPerigosoMapaResponse,
  RodoviaMapaResponse,
} from "../../models/mapa.models";

const GRAVIDADE_COR: Record<Gravidade, string> = {
  FATAL: "#e33d3d",
  GRAVE: "#e8823a",
  LEVE: "#f2b705",
};

const RISCO_COR: Record<string, string> = {
  GRANDE: "#e33d3d",
  MODERADO: "#e8823a",
  BAIXO: "#f2b705",
};

@Component({
  selector: "app-mapa",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "./mapa.component.html",
  styleUrl: "./mapa.component.css",
})
export class MapaComponent implements AfterViewInit, OnDestroy {
  private mapa!: Leaf.Map;
  private camadaAcidentes = Leaf.layerGroup();
  private camadaPerigosos = Leaf.layerGroup();
  private camadaRodovias = Leaf.layerGroup();

  acidentes: AcidenteMapaResponse[] = [];
  pontosPerigosos: PontoPerigosoMapaResponse[] = [];
  rodovias: RodoviaMapaResponse[] = [];

  mostrarAcidentes = true;
  mostrarPerigosos = true;
  mostrarRodovias = true;
  filtroGravidade: Gravidade | "TODAS" = "TODAS";

  itemSelecionado: AcidenteMapaResponse | RodoviaMapaResponse | null = null;
  carregando = true;
  erro: string | null = null;

  constructor(private mapaService: MapaService) {}

  ngAfterViewInit(): void {
    this.iniciarMapa();
    this.carregarDados();
  }

  ngOnDestroy(): void {
    this.mapa?.remove();
  }

  private iniciarMapa(): void {
    this.mapa = Leaf.map("mapa", { zoomControl: false }).setView(
      [-23.0, -46.0],
      6,
    );
    Leaf.control.zoom({ position: "bottomright" }).addTo(this.mapa);

    Leaf.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "&copy; OpenStreetMap contributors",
    }).addTo(this.mapa);

    this.camadaAcidentes.addTo(this.mapa);
    this.camadaPerigosos.addTo(this.mapa);
    this.camadaRodovias.addTo(this.mapa);
  }

  private carregarDados(): void {
    this.carregando = true;
    this.erro = null;

    this.mapaService.buscarRodovias().subscribe({
      next: (rodovias) => {
        this.rodovias = rodovias;
        this.renderizarRodovias();
      },
      error: () =>
        (this.erro = "Não foi possível carregar as rodovias da API."),
    });

    this.mapaService.buscarAcidentes().subscribe({
      next: (acidentes) => {
        this.acidentes = acidentes;
        this.renderizarAcidentes();
        this.carregando = false;
      },
      error: () => {
        this.erro = "Não foi possível carregar os acidentes da API.";
        this.carregando = false;
      },
    });

    this.mapaService.buscarPontosPerigosos().subscribe({
      next: (pontos) => {
        this.pontosPerigosos = pontos;
        this.renderizarPerigosos();
      },
    });
  }

  private renderizarAcidentes(): void {
    this.camadaAcidentes.clearLayers();
    const lista =
      this.filtroGravidade === "TODAS"
        ? this.acidentes
        : this.acidentes.filter((acidente) => acidente.gravidade === this.filtroGravidade);

    lista.forEach((acidente) => {
      const cor = GRAVIDADE_COR[acidente.gravidade] ?? "#999";
      const marcador = Leaf.circleMarker([acidente.latitude, acidente.longitude], {
        radius: 7,
        color: cor,
        weight: 2,
        fillColor: cor,
        fillOpacity: 0.55,
      });
      marcador.bindPopup(`<b>${acidente.tipo}</b><br>${acidente.nomeRodovia}`);
      marcador.on("click", () => (this.itemSelecionado = acidente));
      marcador.addTo(this.camadaAcidentes);
    });
  }

  private renderizarPerigosos(): void {
    this.camadaPerigosos.clearLayers();
    this.pontosPerigosos.forEach((perigo) => {
      const cor = RISCO_COR[perigo.nivelRisco] ?? "#999";
      const marcador = Leaf.circleMarker([perigo.latitude, perigo.longitude], {
        radius: 6,
        color: cor,
        weight: 2,
        fillColor: cor,
        fillOpacity: 0.15,
        dashArray: "2,3",
      });
      marcador.bindPopup(
        `<b>Ponto perigoso</b><br>Risco ${perigo.nivelRisco.toLowerCase()} · ${perigo.nomeRodovia}`,
      );
      marcador.addTo(this.camadaPerigosos);
    });
  }

  private renderizarRodovias(): void {
    this.camadaRodovias.clearLayers();
    this.rodovias.forEach((rodovia) => {
      const pontos = rodovia.tracado.map(
        (p) => [p.latitude, p.longitude] as [number, number],
      );
      const linha = Leaf.polyline(pontos, {
        color: "#f2b705",
        weight: 3,
        opacity: 0.7,
      });
      linha.bindPopup(`<b>${rodovia.nome}</b><br>${rodovia.estado}`);
      linha.on("click", () => (this.itemSelecionado = rodovia));
      linha.addTo(this.camadaRodovias);
    });
  }

  // --- Handlers chamados pelo template ---

  onToggleAcidentes(): void {
    this.mostrarAcidentes
      ? this.camadaAcidentes.addTo(this.mapa)
      : this.mapa.removeLayer(this.camadaAcidentes);
  }

  onTogglePerigosos(): void {
    this.mostrarPerigosos
      ? this.camadaPerigosos.addTo(this.mapa)
      : this.mapa.removeLayer(this.camadaPerigosos);
  }

  onToggleRodovias(): void {
    this.mostrarRodovias
      ? this.camadaRodovias.addTo(this.mapa)
      : this.mapa.removeLayer(this.camadaRodovias);
  }

  onFiltroGravidadeChange(): void {
    this.renderizarAcidentes();
  }

  ehAcidente(
    item: AcidenteMapaResponse | RodoviaMapaResponse,
  ): item is AcidenteMapaResponse {
    return (item as AcidenteMapaResponse).gravidade !== undefined;
  }

  totalMortes(): number {
    return this.acidentes.reduce((soma, acidente) => soma + acidente.fatais, 0);
  }
}
