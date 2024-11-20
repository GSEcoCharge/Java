/* ----------------------- RESET TABLES AND SEQUENCES ----------------------- */

BEGIN TRANSACTION;

-- Clear all tables
DELETE FROM GS_PONTO_PARADA;
DELETE FROM GS_VIAGEM;
DELETE FROM GS_AVALIACAO;
DELETE FROM GS_HISTORICO_CARREGAMENTO;
DELETE FROM GS_RESERVA;
DELETE FROM GS_PONTO_CARREGAMENTO;
DELETE FROM GS_POSTO_CARREGAMENTO;
DELETE FROM GS_VEICULO;
DELETE FROM GS_USUARIO;

-- Reset all sequences
-- DBCC CHECKIDENT ('GS_USUARIO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_VEICULO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_POSTO_CARREGAMENTO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_PONTO_CARREGAMENTO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_RESERVA', RESEED, 0);
-- DBCC CHECKIDENT ('GS_HISTORICO_CARREGAMENTO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_AVALIACAO', RESEED, 0);
-- DBCC CHECKIDENT ('GS_VIAGEM', RESEED, 0);
-- DBCC CHECKIDENT ('GS_PONTO_PARADA', RESEED, 0);

COMMIT;

/* --------------------------------- INSERTS -------------------------------- */

BEGIN TRANSACTION;

INSERT INTO GS_USUARIO (NOME, EMAIL, SENHA, IMG_PERFIL, DATA_CRIACAO, ULTIMA_LOCALIZACAO) VALUES 
('Carlos Santos', 'carlos@example.com', '$2a$12$ehPzo60pKvv3SipPPLxeZ.5l9KxPXQnzYPbIHe1KrOO40N69sPNr6', '1731187862972Front A7X.jpg', '2021-10-01 10:00:00', 'BH'),
('Ana Silva', 'ana.silva@example.com', '$2a$12$4CN8.fpvIzYhs3xOwJEdoOSujtz.Jd3NEcAelFwSwTMzhmu88uMSW', '1731186754029Front A7X.jpg', '2021-10-02 11:00:00', 'SP'),
('José Oliveira', 'jose.oliveira@example.com', '$2a$12$3d1IaQnqTEjHCDxtAfyiH.4GP5K2eBiGP5vxjMqxEwJvpX.MBADT2', '1731187375710Cover - Copia.jpg', '2021-10-03 12:00:00', 'RJ'),
('Mariana Souza', 'mariana.souza@example.com', '$2a$12$16BzbIppE67oTqpQKgwyG.voerMWSB9hOKuL4GFQBKaaX7Mb/aU6O', '1731187853980Cover - Copia.jpg', '2021-10-04 13:00:00', 'MG'),
('Pedro Lima', 'pedro.lima@example.com', '$2a$12$S9hoBrf.1si63mkqGkDVNesHPRDYesh6qCdqupkT1wP2bu.ct9Qye', '1731186444622Front A7X.jpg', '2021-10-05 14:00:00', 'BA');

COMMIT;

SELECT * FROM GS_USUARIO;

BEGIN TRANSACTION;

INSERT INTO GS_VEICULO (USUARIO_ID, MARCA, MODELO, ANO, AUTONOMIA, TIPO_CONECTOR) VALUES 
(1, 'Tesla', 'Model S', 2021, 500, 'Tipo 2'),
(2, 'Nissan', 'Leaf', 2019, 240, 'CHAdeMO'),
(3, 'Chevrolet', 'Bolt', 2020, 380, 'CSS'),
(4, 'BMW', 'i3', 2018, 290, 'Tipo 2'),
(5, 'Renault', 'Zoe', 2017, 300, 'CSS');

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_POSTO_CARREGAMENTO (NOME, LATITUDE, LONGITUDE, ENDERECO, HORARIO_FUNCIONAMENTO, FORMAS_PAGAMENTO, AVALIACAO_MEDIA) VALUES 
('Posto Central', -23.5558, -46.6396, 'Rua A, SP', '24 horas', 'Cartão, Dinheiro', 4.8),
('EletroPosto', -22.9035, -43.2096, 'Av B, RJ', '08:00-20:00', 'Cartão', 4.5),
('EcoCharge', -19.9191, -43.9386, 'Rua C, BH', '06:00-22:00', 'Cartão, Pix', 4.7),
('Carga Rápida', -30.0277, -51.2287, 'Av D, POA', '24 horas', 'Dinheiro, Pix', 4.3),
('PowerCharge', -12.9714, -38.5014, 'Av E, SSA', '07:00-23:00', 'Cartão', 4.6);

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_PONTO_CARREGAMENTO (POSTO_ID, TIPO_CONECTOR, VELOCIDADE_CARREGAMENTO, DISPONIBILIDADE, RESERVAVEL) VALUES 
(1, 'Tipo 2', 22, 'disponível', 'S'),
(2, 'CHAdeMO', 50, 'ocupado', 'N'),
(3, 'CSS', 100, 'ocupado', 'S'),
(4, 'Tipo 2', 43, 'disponível', 'N'),
(5, 'CSS', 75, 'disponível', 'S');

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_RESERVA (USUARIO_ID, PONTO_ID, DATA_RESERVA, STATUS) VALUES 
(1, 1, '2021-10-10 10:00:00', 'ativa'),
(2, 2, '2021-10-11 11:00:00', 'ativa'),
(3, 3, '2021-10-12 12:00:00', 'cancelada'),
(4, 4, '2021-10-13 13:00:00', 'concluída'),
(5, 5, '2021-10-14 14:00:00', 'ativa');

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_HISTORICO_CARREGAMENTO (USUARIO_ID, PONTO_ID, ENERGIA_CONSUMIDA, EMISSOES_EVITADAS) VALUES 
(1, 1, 10.5, 0.8),
(2, 2, 15.0, 1.1),
(3, 3, 20.2, 1.5),
(4, 4, 8.0, 0.6),
(5, 5, 12.5, 1.0);

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_AVALIACAO (USUARIO_ID, POSTO_ID, NOTA, COMENTARIO) VALUES 
(1, 1, 5, 'Excelente posto!'),
(2, 2, 4, 'Bom, mas poderia ser mais rápido.'),
(3, 3, 3, 'Regular, teve fila.'),
(4, 4, 5, 'Ótimo atendimento.'),
(5, 5, 2, 'Não recomendo.');

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_VIAGEM (USUARIO_ID, ORIGEM, DESTINO, AUTONOMIA_RESTANTE) VALUES 
(1, 'São Paulo', 'Rio de Janeiro', 300),
(2, 'Belo Horizonte', 'Brasília', 250),
(3, 'Curitiba', 'Florianópolis', 200),
(4, 'Porto Alegre', 'Montevidéu', 150),
(5, 'Salvador', 'Recife', 350);

COMMIT;

BEGIN TRANSACTION;

INSERT INTO GS_PONTO_PARADA (VIAGEM_ID, PONTO_ID, ORDEM) VALUES 
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1);

COMMIT;