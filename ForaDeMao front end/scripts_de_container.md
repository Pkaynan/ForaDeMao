# Scripts de Container — Fora de Mão

Comandos para criar a imagem e executar o frontend utilizando Podman ou Docker.

---

## Podman

### Build da imagem
Cria a imagem `fora-de-mao-frontend` a partir do contexto atual:

```bash
podman build -t fora-de-mao-frontend .
```

Para apenas criar a imagem, sem executar o container, utilize o comando acima.

### Executar o frontend
Executa o container utilizando a imagem `fora-de-mao-frontend`:

```bash
podman run --rm -it \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```

O frontend ficará disponível na porta:
`http://localhost:4200`

### Executar apenas com a porta exposta
```bash
podman run --rm -it -p 4200:4200
```
> **Nota:** Esse comando precisa receber uma imagem para ser executado corretamente.

---

## Docker

### Build da imagem
Cria a imagem utilizando o Containerfile:

```bash
docker build -f Containerfile -t fora-de-mao-frontend .
```

### Executar o frontend
```bash
docker run --rm -it \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```

O frontend ficará disponível na porta:
`http://localhost:4200`

### Executar apenas com a porta exposta
```bash
docker run --rm -it -p 4200:4200
```
> **Nota:** Esse comando também precisa receber uma imagem para ser executado corretamente.

---

## Diferença entre as flags

* `-i`: Mantém a entrada padrão (stdin) aberta.
* `-t`: Cria um terminal interativo (TTY).
* `-it`: Combina as duas opções. É útil para executar o container de forma interativa.

### Executar sem TTY
Caso não queira criar um terminal interativo, remova apenas a flag `-t`:

**Com Podman:**
```bash
podman run --rm -i \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```

**Com Docker:**
```bash
docker run --rm -i \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```

---

## Resumo dos comandos

| Ação | Podman | Docker |
| :--- | :--- | :--- |
| **Criar imagem** | `podman build -t fora-de-mao-frontend .` | `docker build -f Containerfile -t fora-de-mao-frontend .` |
| **Executar** | `podman run --rm -it ...` | `docker run --rm -it ...` |
| **Porta** | `4200:4200` | `4200:4200` |
| **Remover container ao sair** | `--rm` | `--rm` |
| **Terminal interativo** | `-it` | `-it` |

---

## Fluxo recomendado

### Podman
```bash
podman build -t fora-de-mao-frontend .

podman run --rm -it \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```

### Docker
```bash
docker build -f Containerfile -t fora-de-mao-frontend .

docker run --rm -it \
  -p 4200:4200 \
  -v "$PWD:/app" \
  -v /app/node_modules \
  fora-de-mao-frontend
```