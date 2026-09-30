CREATE DATABASE IF NOT EXISTS forademao
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE forademao;

-- ---------------------------------------------------------------------
-- CIDADE
-- ---------------------------------------------------------------------
CREATE TABLE cidade (
    idcidade   BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(120) NOT NULL,
    estado     CHAR(2)      NOT NULL,
    INDEX idx_cidade_estado (estado)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- RODOVIA
-- ---------------------------------------------------------------------
CREATE TABLE rodovia (
    idrodovia  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(60)  NOT NULL,
    tipo       VARCHAR(40)  NOT NULL,
    estado     VARCHAR(10)  NOT NULL,
    km         INT          NOT NULL DEFAULT 0,
    INDEX idx_rodovia_estado (estado),
    INDEX idx_rodovia_nome (nome)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- RODOVIA_PONTO — traçado geográfico (linha) de cada rodovia.
-- A sequência ordenada por "ordem" forma a polyline desenhada no mapa.
-- ---------------------------------------------------------------------
CREATE TABLE rodovia_ponto (
    idrodovia_ponto  BIGINT AUTO_INCREMENT PRIMARY KEY,
    idrodovia        BIGINT NOT NULL,
    latitude         DECIMAL(9,6)  NOT NULL,
    longitude        DECIMAL(9,6)  NOT NULL,
    ordem            INT           NOT NULL,
    CONSTRAINT fk_rodoviaponto_rodovia
        FOREIGN KEY (idrodovia) REFERENCES rodovia (idrodovia)
        ON DELETE CASCADE,
    UNIQUE KEY uq_rodovia_ordem (idrodovia, ordem),
    INDEX idx_rodoviaponto_latlon (latitude, longitude)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- CONEXAO_RODOVIA — liga duas cidades por uma rodovia
-- ---------------------------------------------------------------------
CREATE TABLE conexao_rodovia (
    idconexao       BIGINT AUTO_INCREMENT PRIMARY KEY,
    idrodovia       BIGINT NOT NULL,
    cidade_origem   BIGINT NOT NULL,
    cidade_destino  BIGINT NOT NULL,
    distancia_km    INT    NOT NULL,
    CONSTRAINT fk_conexao_rodovia
        FOREIGN KEY (idrodovia) REFERENCES rodovia (idrodovia)
        ON DELETE CASCADE,
    CONSTRAINT fk_conexao_cidade_origem
        FOREIGN KEY (cidade_origem) REFERENCES cidade (idcidade),
    CONSTRAINT fk_conexao_cidade_destino
        FOREIGN KEY (cidade_destino) REFERENCES cidade (idcidade),
    INDEX idx_conexao_rodovia (idrodovia)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- FLUXO_RODOVIA — volume/fluxo de tráfego por rodovia
-- ---------------------------------------------------------------------
CREATE TABLE fluxo_rodovia (
    idfluxo    BIGINT AUTO_INCREMENT PRIMARY KEY,
    idrodovia  BIGINT NOT NULL,
    CONSTRAINT fk_fluxo_rodovia
        FOREIGN KEY (idrodovia) REFERENCES rodovia (idrodovia)
        ON DELETE CASCADE,
    INDEX idx_fluxo_rodovia (idrodovia)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- PONTOS_PERIGOSOS
-- ---------------------------------------------------------------------
CREATE TABLE pontos_perigosos (
    idpontos_perigosos  BIGINT AUTO_INCREMENT PRIMARY KEY,
    idrodovia           BIGINT NOT NULL,
    latitude             DECIMAL(9,6) NOT NULL,
    longitude            DECIMAL(9,6) NOT NULL,
    nivel_risco          ENUM('GRANDE','MODERADO','BAIXO') NOT NULL,
    CONSTRAINT fk_pontosperigosos_rodovia
        FOREIGN KEY (idrodovia) REFERENCES rodovia (idrodovia)
        ON DELETE CASCADE,
    INDEX idx_pontosperigosos_latlon (latitude, longitude),
    INDEX idx_pontosperigosos_risco (nivel_risco)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- ACIDENTE
-- ---------------------------------------------------------------------
CREATE TABLE acidente (
    idacidente  BIGINT AUTO_INCREMENT PRIMARY KEY,
    idrodovia   BIGINT NOT NULL,
    gravidade   ENUM('LEVE','GRAVE','FATAL') NOT NULL,
    tipo        ENUM('COLISAO','TOMBAMENTO','ATROPELAMENTO') NOT NULL,
    latitude    DECIMAL(9,6) NOT NULL,
    longitude   DECIMAL(9,6) NOT NULL,
    fatais      INT NOT NULL DEFAULT 0,
    feridos     INT NOT NULL DEFAULT 0,
    ilesos      INT NOT NULL DEFAULT 0,
    data_hora   DATETIME NOT NULL,
    CONSTRAINT fk_acidente_rodovia
        FOREIGN KEY (idrodovia) REFERENCES rodovia (idrodovia)
        ON DELETE CASCADE,
    -- índice composto para a busca por bounding box (a query do mapa
    -- filtra latitude e longitude juntas, ver AcidenteRepository)
    INDEX idx_acidente_latlon (latitude, longitude),
    INDEX idx_acidente_rodovia (idrodovia),
    INDEX idx_acidente_gravidade (gravidade),
    INDEX idx_acidente_data (data_hora)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- CLIMA — condições climáticas associadas a um acidente
-- ---------------------------------------------------------------------
CREATE TABLE clima (
    idclima     BIGINT AUTO_INCREMENT PRIMARY KEY,
    idacidente  BIGINT NOT NULL,
    descricao   VARCHAR(120) NOT NULL,
    CONSTRAINT fk_clima_acidente
        FOREIGN KEY (idacidente) REFERENCES acidente (idacidente)
        ON DELETE CASCADE,
    INDEX idx_clima_acidente (idacidente)
) ENGINE=InnoDB;

-- =====================================================================
-- Dados de exemplo (opcional) — mesmos pontos usados no mock do frontend
-- =====================================================================
INSERT INTO rodovia (nome, tipo, estado, km) VALUES
  ('BR-116', 'Federal', 'SP', 0),
  ('BR-101', 'Federal', 'RJ', 0),
  ('BR-050', 'Federal', 'SP/MG', 0);

INSERT INTO rodovia_ponto (idrodovia, latitude, longitude, ordem) VALUES
  (1, -23.550000, -46.630000, 0),
  (1, -23.600000, -46.600000, 1),
  (1, -23.700000, -46.550000, 2),
  (1, -23.850000, -46.450000, 3),
  (2, -22.900000, -43.200000, 0),
  (2, -22.850000, -43.280000, 1),
  (2, -22.800000, -43.350000, 2),
  (2, -22.750000, -43.420000, 3),
  (3, -21.760000, -48.180000, 0),
  (3, -21.300000, -48.200000, 1),
  (3, -20.750000, -48.250000, 2),
  (3, -20.470000, -48.280000, 3);

INSERT INTO acidente (idrodovia, gravidade, tipo, latitude, longitude, fatais, feridos, ilesos, data_hora) VALUES
  (1, 'GRAVE', 'COLISAO',      -23.550500, -46.633300, 0, 3, 1, '2026-08-10 14:20:00'),
  (1, 'FATAL', 'TOMBAMENTO',   -23.562900, -46.654400, 1, 2, 0, '2026-07-22 02:05:00'),
  (2, 'FATAL', 'ATROPELAMENTO',-22.908300, -43.196400, 1, 0, 0, '2026-06-14 19:40:00'),
  (2, 'LEVE',  'COLISAO',      -22.830500, -43.320000, 0, 1, 2, '2026-09-01 09:10:00'),
  (3, 'GRAVE', 'TOMBAMENTO',   -21.764200, -48.175500, 0, 2, 1, '2026-05-30 17:55:00'),
  (3, 'FATAL', 'COLISAO',      -20.469700, -48.282200, 2, 1, 0, '2026-04-11 22:30:00');

INSERT INTO pontos_perigosos (idrodovia, latitude, longitude, nivel_risco) VALUES
  (1, -23.545000, -46.625000, 'GRANDE'),
  (2, -22.870000, -43.260000, 'MODERADO'),
  (3, -21.000000, -48.220000, 'GRANDE');