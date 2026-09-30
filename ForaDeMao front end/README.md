# Fora de Mão — Frontend (Angular + Leaflet)

## Rodar localmente
1. `npm install`
2. Confira `src/environments/environment.ts` — por padrão aponta para
   `http://localhost:8080/api/mapa` (o backend Spring Boot deste projeto).
3. `npm start` (abre em http://localhost:4200)

## Estrutura
- `src/app/models/mapa.models.ts` — interfaces TypeScript espelhando os DTOs do backend
- `src/app/services/mapa.service.ts` — chamadas HTTP para `/acidentes`, `/rodovias`, `/pontos-perigosos`
- `src/app/components/mapa/` — componente do mapa (Leaflet), com camadas, filtro por gravidade e painel de detalhe

## Backend
O backend correspondente (Spring Boot + MySQL) está no diretório `backend-mapa`,
com CORS já liberado para `http://localhost:4200`.
