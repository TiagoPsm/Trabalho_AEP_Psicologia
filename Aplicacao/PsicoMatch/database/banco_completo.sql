CREATE DATABASE IF NOT EXISTS trabalho_psicologia;

USE trabalho_psicologia;

CREATE TABLE IF NOT EXISTS profissional (
    profissional_cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    profissional_nome VARCHAR(100) NOT NULL,
    profissional_telefone VARCHAR(40) NOT NULL UNIQUE,
    profissional_email VARCHAR(150) UNIQUE,
    profissional_valor_consulta DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS clinica (
    clinica_cnpj VARCHAR(14) NOT NULL PRIMARY KEY,
    clinica_nome VARCHAR(50) NOT NULL,
    clinica_rua VARCHAR(100) NOT NULL,
    clinica_bairro VARCHAR(40) NOT NULL,
    clinica_complemento VARCHAR(150),
    clinica_numero VARCHAR(250) NOT NULL,
    clinica_media_custo DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS paciente (
    paciente_cpf VARCHAR(11) PRIMARY KEY,
    paciente_nome VARCHAR(100) NOT NULL,
    paciente_telefone VARCHAR(40) NOT NULL,
    paciente_endereco VARCHAR(250) NOT NULL,
    profissional_cpf VARCHAR(11),

    CONSTRAINT fk_paciente_profissional
        FOREIGN KEY (profissional_cpf)
        REFERENCES profissional (profissional_cpf)
        ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS prontuario (
    prontuario_id INT PRIMARY KEY,
    paciente_cpf VARCHAR(11) NOT NULL,
    profissional_cpf VARCHAR(11) NOT NULL,
    descricao VARCHAR(500),

    CONSTRAINT fk_prontuario_paciente
        FOREIGN KEY (paciente_cpf)
        REFERENCES paciente (paciente_cpf)
	ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_prontuario_profissional
        FOREIGN KEY (profissional_cpf)
        REFERENCES profissional (profissional_cpf)
        ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS agendamento (
    agendamento_id INT PRIMARY KEY,
    agendamento_data DATE NOT NULL,
    agendamento_horario TIME NOT NULL,
    clinica_cnpj VARCHAR(14) NOT NULL,
    profissional_cpf VARCHAR(11) NOT NULL,
    paciente_cpf VARCHAR(11) NOT NULL,

    CONSTRAINT fk_agendamento_paciente
        FOREIGN KEY (paciente_cpf)
        REFERENCES paciente (paciente_cpf)
        ON UPDATE CASCADE
	ON DELETE CASCADE,

    CONSTRAINT fk_agendamento_profissional
        FOREIGN KEY (profissional_cpf)
        REFERENCES profissional (profissional_cpf)
        ON UPDATE CASCADE,

    CONSTRAINT fk_agendamento_clinica
        FOREIGN KEY (clinica_cnpj)
        REFERENCES clinica (clinica_cnpj)
        ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS equipe (
    clinica_cnpj VARCHAR(14),
    profissional_cpf VARCHAR(11),

    PRIMARY KEY (clinica_cnpj, profissional_cpf),

    CONSTRAINT fk_equipe_clinica
        FOREIGN KEY (clinica_cnpj)
        REFERENCES clinica (clinica_cnpj)
        ON UPDATE CASCADE,

    CONSTRAINT fk_equipe_profissional
        FOREIGN KEY (profissional_cpf)
        REFERENCES profissional (profissional_cpf)
        ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS log_paciente (
	log_id INT AUTO_INCREMENT PRIMARY KEY,
	log_alterado_de_paciente_cpf VARCHAR(11),
	log_alterado_para_paciente_cpf VARCHAR(11),
	log_alterado_de_paciente_nome VARCHAR(100),
	log_alterado_para_paciente_nome VARCHAR(100),
	log_alterado_de_paciente_telefone VARCHAR (40),
	log_alterado_para_paciente_telefone VARCHAR (40),
	log_data DATETIME,
	log_alterado_de_profissional_cpf VARCHAR(11),
	log_alterado_para_profissional_cpf VARCHAR(11),
	log_usuario VARCHAR(30),
	log_operacao VARCHAR(30)
);

INSERT IGNORE INTO profissional (profissional_cpf, profissional_nome, profissional_telefone, profissional_email, profissional_valor_consulta)
VALUES
    ('12345678901', 'Dra. Ana Paula Souza', '(11) 98765-4321', 'ana.souza@psicologia.com.br', 180.00),
    ('23456789012', 'Dr. Carlos Alberto Mendes', '(11) 97654-3210', 'carlos.mendes@psicologia.com.br', 200.00),
    ('34567890123', 'Dra. Beatriz Oliveira Lima', '(11) 96543-2109', 'beatriz.lima@psicologia.com.br', 200.00),
    ('45678901234', 'Dr. Ricardo Fernandes Santos', '(11) 95432-1098', 'ricardo.santos@psicologia.com.br', 190.00),
    ('56789012345', 'Dra. Camila Rodrigues Costa', '(11) 94321-0987', 'camila.costa@psicologia.com.br', 210.00),
    ('67890123456', 'Dra. Fernanda Castro Rocha', '(11) 93210-9876', 'fernanda.castro@psicologia.com.br', 195.00),
    ('78901234567', 'Dr. Gustavo Henrique Nunes', '(11) 92109-8765', 'gustavo.nunes@psicologia.com.br', 215.00),
    ('89012345678', 'Dra. Larissa Santos Pereira', '(11) 91098-7654', 'larissa.pereira@psicologia.com.br', 185.00),
    ('90123456789', 'Dr. Marcelo Ribeiro Almeida', '(11) 90987-6543', 'marcelo.almeida@psicologia.com.br', 230.00),
    ('01234567890', 'Dra. Renata Carvalho Lima', '(11) 89876-5432', 'renata.lima@psicologia.com.br', 205.00);

INSERT IGNORE INTO clinica (clinica_cnpj, clinica_nome, clinica_rua, clinica_bairro, clinica_complemento, clinica_numero)
VALUES
    ('12345678000199', 'Clínica Bem-Estar', 'Rua das Palmeiras', 'Jardim América', 'Sala 201', '450'),
    ('98765432000188', 'Centro Psicológico Vida', 'Avenida Paulista', 'Bela Vista', 'Conjunto 34', '1578'),
    ('45678912000177', 'Espaço Terapêutico Serenidade', 'Rua dos Jacarandás', 'Moema', 'Bloco B', '789'),
    ('32165498000166', 'Clínica Mente Sã', 'Rua Augusta', 'Consolação', 'Fundos', '2345'),
    ('65498732000155', 'Clínica Equilíbrio Mental', 'Rua dos Girassóis', 'Vila Mariana', 'Sala 12', '567'),
    ('78912345000144', 'Centro de Psicologia Acolher', 'Rua das Hortênsias', 'Ipiranga', 'Conjunto 5', '890'),
    ('85296374000133', 'Instituto de Terapia Integrativa', 'Rua dos Cravos', 'Perdizes', 'Bloco A', '1234'),
    ('14725836000122', 'Clínica Nova Vida', 'Rua dos Ipês', 'Brooklin', 'Sala 101', '789'),
    ('36925814000111', 'Espaço Terapêutico Renascer', 'Avenida dos Bandeirantes', 'Morumbi', 'Bloco C', '2345'),
    ('25814736000100', 'Centro de Psicologia Saber', 'Rua das Camélias', 'Vila Madalena', 'Conjunto 8', '456');

INSERT IGNORE INTO paciente (paciente_cpf, paciente_nome, paciente_telefone, paciente_endereco, profissional_cpf)
VALUES
    ('11122233344', 'João Pedro Silva', '(11) 91234-5678', 'Rua das Acácias, 123, Apto 45, Jardim das Flores', '12345678901'),
    ('22233344455', 'Maria Fernanda Oliveira', '(11) 92345-6789', 'Avenida Brasil, 456, Casa 12, Vila Nova', '23456789012'),
    ('33344455566', 'Carlos Eduardo Santos', '(11) 93456-7890', 'Rua dos Pinheiros, 789, Bloco C, Centro', NULL),
    ('44455566677', 'Ana Beatriz Rodrigues', '(11) 94567-8901', 'Praça da Liberdade, 321, Apto 101, Liberdade', '34567890123'),
    ('55566677788', 'Paulo Roberto Gomes', '(11) 95678-9012', 'Rua das Orquídeas, 654, Casa 7, Jardim Botânico', NULL),
    ('66677788899', 'Mariana Souza Lima', '(11) 96789-0123', 'Rua dos Lírios, 987, Apto 56, Vila Verde', '45678901234'),
    ('77788899900', 'Fernando Henrique Alves', '(11) 97890-1234', 'Avenida dos Estados, 147, Sala 3, Centro', NULL),
    ('88899900011', 'Patrícia Cardoso Ferreira', '(11) 98901-2345', 'Rua das Margaridas, 258, Casa 9, Jardim Primavera', '56789012345'),
    ('99988877766', 'Rodrigo Augusto Martins', '(11) 91234-9876', 'Rua dos Cedros, 345, Apto 12, Jardim Paulista', '67890123456'),
    ('88877766655', 'Tatiana Mendes Soares', '(11) 92345-8765', 'Avenida Ipiranga, 789, Casa 3, Bela Vista', '78901234567');

INSERT IGNORE INTO equipe (clinica_cnpj, profissional_cpf)
VALUES
    ('12345678000199', '12345678901'),
    ('12345678000199', '23456789012'),
    ('98765432000188', '34567890123'),
    ('98765432000188', '45678901234'),
    ('45678912000177', '56789012345'),
    ('45678912000177', '12345678901'),
    ('32165498000166', '23456789012'),
    ('32165498000166', '34567890123'),
    ('65498732000155', '67890123456'),
    ('78912345000144', '89012345678');

INSERT IGNORE INTO prontuario (prontuario_id, paciente_cpf, profissional_cpf, descricao)
VALUES
    (1, '11122233344', '12345678901', 'Paciente apresenta sintomas de ansiedade generalizada. Relata preocupação excessiva com trabalho e insônia há 3 meses. Iniciaremos terapia cognitivo-comportamental.'),
    (2, '11122233344', '12345678901', 'Segunda sessão. Paciente demonstrou melhora na identificação de pensamentos automáticos. Técnicas de respiração estão sendo bem assimiladas.'),
    (3, '22233344455', '23456789012', 'Paciente com queixas de tristeza profunda e falta de motivação. Histórico de luto recente. Avaliação inicial aponta possível quadro depressivo.'),
    (4, '44455566677', '34567890123', 'Paciente relata dificuldades no relacionamento conjugal. Possível dependência emocional. Iniciaremos abordagem focada na autonomia.'),
    (5, '66677788899', '45678901234', 'Paciente com fobia social. Relata sudorese e taquicardia em situações sociais. Planejado exposição gradual e técnicas de relaxamento.'),
    (6, '88899900011', '56789012345', 'Paciente com queixas de estresse pós-traumático. Relata episódio de assalto há 6 meses. Iniciaremos EMDR e terapia de exposição.'),
    (7, '11122233344', '12345678901', 'Terceira sessão. Paciente demonstrou progresso significativo. Relata melhora na qualidade do sono. Continuação das técnicas de mindfulness.'),
    (8, '22233344455', '23456789012', 'Quarta sessão. Paciente ainda com sinais de depressão, mas iniciou atividades físicas. Medicado pelo psiquiatra. Acompanhamento conjunto.'),
    (9, '44455566677', '34567890123', 'Sexta sessão. Paciente começou a reconhecer padrões de relacionamento disfuncionais. Avanço na autonomia emocional.'),
    (10, '66677788899', '45678901234', 'Quinta sessão. Paciente conseguiu enfrentar situação social com menos ansiedade. Técnicas de exposição estão sendo eficazes.');

INSERT IGNORE INTO agendamento (agendamento_id, agendamento_data, agendamento_horario, clinica_cnpj, profissional_cpf, paciente_cpf)
VALUES
    (1, '2026-09-01', '09:00:00', '12345678000199', '12345678901', '11122233344'),
    (2, '2026-09-01', '10:30:00', '12345678000199', '12345678901', '11122233344'),
    (3, '2026-09-02', '14:00:00', '12345678000199', '23456789012', '22233344455'),
    (4, '2026-09-02', '15:30:00', '98765432000188', '34567890123', '44455566677'),
    (5, '2026-09-03', '11:00:00', '98765432000188', '45678901234', '66677788899'),
    (6, '2026-09-03', '16:00:00', '45678912000177', '56789012345', '88899900011'),
    (7, '2026-09-04', '08:30:00', '12345678000199', '12345678901', '11122233344'),
    (8, '2026-09-04', '13:00:00', '12345678000199', '23456789012', '22233344455'),
    (9, '2026-09-05', '10:00:00', '32165498000166', '34567890123', '44455566677'),
    (10, '2026-09-06', '09:30:00', '98765432000188', '45678901234', '66677788899'),
    (11, '2026-09-06', '14:30:00', '45678912000177', '56789012345', '88899900011'),
    (12, '2026-09-07', '11:30:00', '12345678000199', '12345678901', '33344455566'),
    (13, '2026-09-07', '15:00:00', '98765432000188', '34567890123', '55566677788'),
    (14, '2026-09-08', '09:00:00', '12345678000199', '23456789012', '77788899900');

SELECT paciente.paciente_nome, profissional.profissional_nome
FROM paciente
JOIN profissional ON paciente.profissional_cpf = profissional.profissional_cpf;

SELECT profissional.profissional_nome, clinica.clinica_nome
FROM profissional

INNER JOIN equipe ON profissional.profissional_cpf = equipe.profissional_cpf
INNER JOIN clinica ON equipe.clinica_cnpj = clinica.clinica_cnpj

WHERE clinica.clinica_nome = 'Clínica Bem-Estar';

SELECT agendamento_data, paciente_nome
FROM agendamento

INNER JOIN paciente ON agendamento.paciente_cpf = paciente.paciente_cpf

WHERE agendamento.agendamento_data = '2026-09-07';

SELECT *
FROM agendamento

INNER JOIN clinica ON agendamento.clinica_cnpj = clinica.clinica_cnpj

WHERE clinica.clinica_nome = 'Espaço Terapêutico Serenidade';

SELECT clinica.clinica_nome
FROM clinica

WHERE clinica.clinica_cnpj = '65498732000155';

SELECT prontuario.paciente_cpf, paciente.paciente_nome, profissional.profissional_nome
FROM prontuario

INNER JOIN paciente ON prontuario.paciente_cpf = paciente.paciente_cpf
INNER JOIN profissional ON prontuario.profissional_cpf = profissional.profissional_cpf

WHERE prontuario.paciente_cpf = '88899900011';

SELECT equipe.profissional_cpf, equipe.clinica_cnpj, profissional.profissional_nome
FROM equipe

INNER JOIN profissional ON equipe.profissional_cpf = profissional.profissional_cpf

WHERE equipe.clinica_cnpj = '12345678000199';

DROP TRIGGER IF EXISTS trg_paciente_after_update;

DELIMITER $$

CREATE TRIGGER trg_paciente_after_update
AFTER UPDATE ON paciente
FOR EACH ROW
BEGIN
    INSERT INTO log_paciente (
        log_alterado_de_paciente_cpf,
        log_alterado_para_paciente_cpf,
        log_alterado_de_paciente_nome,
        log_alterado_para_paciente_nome,
        log_alterado_de_paciente_telefone,
        log_alterado_para_paciente_telefone,
        log_data,
        log_alterado_de_profissional_cpf,
        log_alterado_para_profissional_cpf,
        log_usuario,
        log_operacao
    )
    VALUES (
        OLD.paciente_cpf,
        NEW.paciente_cpf,
        OLD.paciente_nome,
        NEW.paciente_nome,
        OLD.paciente_telefone,
        NEW.paciente_telefone,
        NOW(),
        OLD.profissional_cpf,
        NEW.profissional_cpf,
        CURRENT_USER(),
        'UPDATE'
    );
END$$

DELIMITER ;

DROP TRIGGER IF EXISTS trg_paciente_before_delete;

DELIMITER $$

CREATE TRIGGER trg_paciente_before_delete
BEFORE DELETE ON paciente
FOR EACH ROW
BEGIN
    INSERT INTO log_paciente (
        log_alterado_de_paciente_cpf,
        log_alterado_para_paciente_cpf,
        log_alterado_de_paciente_nome,
        log_alterado_para_paciente_nome,
        log_alterado_de_paciente_telefone,
        log_alterado_para_paciente_telefone,
        log_data,
        log_alterado_de_profissional_cpf,
        log_alterado_para_profissional_cpf,
        log_usuario,
        log_operacao
    )
    VALUES (
        OLD.paciente_cpf,
        NULL,
        OLD.paciente_nome,
        NULL,
        OLD.paciente_telefone,
        NULL,
        NOW(),
        OLD.profissional_cpf,
        NULL,
        CURRENT_USER(),
        'DELETE'
    );
END$$

DELIMITER ;

DROP TRIGGER IF EXISTS trg_paciente_after_insert;

DELIMITER $$

CREATE TRIGGER trg_paciente_after_insert
AFTER INSERT ON paciente
FOR EACH ROW
BEGIN
    INSERT INTO log_paciente (
        log_alterado_de_paciente_cpf,
        log_alterado_para_paciente_cpf,
        log_alterado_de_paciente_nome,
        log_alterado_para_paciente_nome,
        log_alterado_de_paciente_telefone,
        log_alterado_para_paciente_telefone,
        log_data,
        log_alterado_de_profissional_cpf,
        log_alterado_para_profissional_cpf,
        log_usuario,
        log_operacao
    )
    VALUES (
        NULL,
        NEW.paciente_cpf,
        NULL,
        NEW.paciente_nome,
        NULL,
        NEW.paciente_telefone,
        NOW(),
        NULL,
        NEW.profissional_cpf,
        CURRENT_USER(),
	'INSERT'

    );
END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS sp_obter_historico_paciente;

DELIMITER $$

CREATE PROCEDURE sp_obter_historico_paciente(
    IN p_paciente_cpf VARCHAR(11)
)
BEGIN
    SELECT
        paciente_nome AS 'Paciente',
        prontuario_id AS 'Nº Prontuário',
        profissional_nome AS 'Psicólogo(a)',
        descricao AS 'Evolução Clínica',
        clinica_nome AS 'Clínica Associada'

    FROM prontuario

    INNER JOIN paciente ON prontuario.paciente_cpf = paciente.paciente_cpf
    INNER JOIN profissional ON prontuario.profissional_cpf = profissional.profissional_cpf
    INNER JOIN equipe ON profissional.profissional_cpf = equipe.profissional_cpf
    INNER JOIN clinica ON equipe.clinica_cnpj = clinica.clinica_cnpj

    WHERE paciente.paciente_cpf = p_paciente_cpf;
END$$

DELIMITER ;

CREATE OR REPLACE VIEW vw_agendamentos AS
SELECT
    agendamento.agendamento_id,
    agendamento.agendamento_data,
    agendamento.agendamento_horario,
    agendamento.paciente_cpf,
    profissional.profissional_nome,
    paciente.paciente_nome,
    clinica.clinica_nome
FROM agendamento
INNER JOIN profissional
    ON agendamento.profissional_cpf = profissional.profissional_cpf
INNER JOIN clinica
    ON agendamento.clinica_cnpj = clinica.clinica_cnpj
INNER JOIN paciente
    ON agendamento.paciente_cpf = paciente.paciente_cpf;

DROP PROCEDURE IF EXISTS alterar_agendamento;

DELIMITER $$

CREATE PROCEDURE alterar_agendamento(
    IN novo_dia DATE,
    IN novo_horario TIME,
    IN p_paciente_cpf VARCHAR(11)
)
BEGIN

    SELECT *
    FROM vw_agendamentos
    WHERE paciente_cpf = p_paciente_cpf;

    UPDATE agendamento
    SET agendamento_data = novo_dia,
        agendamento_horario = novo_horario
    WHERE agendamento.paciente_cpf = p_paciente_cpf;

END $$

DELIMITER ;

CREATE OR REPLACE VIEW  vw_media_custo_clinica AS
SELECT
    c.clinica_cnpj,
    c.clinica_nome,
    AVG(p.profissional_valor_consulta) AS media_custo
FROM clinica c
INNER JOIN equipe e
    ON c.clinica_cnpj = e.clinica_cnpj
INNER JOIN profissional p
    ON e.profissional_cpf = p.profissional_cpf
GROUP BY
    c.clinica_cnpj,
    c.clinica_nome;

DROP TRIGGER IF EXISTS trg_atualiza_media_custo;

DELIMITER $$

CREATE TRIGGER trg_atualiza_media_custo
AFTER UPDATE ON profissional
FOR EACH ROW
BEGIN

    UPDATE clinica c
    INNER JOIN vw_media_custo_clinica v
        ON c.clinica_cnpj = v.clinica_cnpj
    INNER JOIN equipe e
        ON c.clinica_cnpj = e.clinica_cnpj
    SET c.clinica_media_custo = v.media_custo
    WHERE e.profissional_cpf = NEW.profissional_cpf;

END$$

DELIMITER ;

DROP PROCEDURE IF EXISTS alterar_valor_consulta;

DELIMITER $$

CREATE PROCEDURE alterar_valor_consulta(
    IN p_profissional_cpf VARCHAR(11),
    IN p_novo_valor DECIMAL(10,2)
)
BEGIN

    UPDATE profissional
    SET profissional_valor_consulta = p_novo_valor
    WHERE profissional_cpf = p_profissional_cpf;

END$$

DELIMITER ;

UPDATE clinica c
INNER JOIN vw_media_custo_clinica v
    ON c.clinica_cnpj = v.clinica_cnpj
SET c.clinica_media_custo = v.media_custo;

SELECT
    clinica_cnpj,
    clinica_nome,
    clinica_media_custo
FROM clinica;

SELECT *
FROM vw_media_custo_clinica;

CREATE OR REPLACE VIEW  relatorio_paciente AS
SELECT
    paciente.paciente_cpf,
    paciente.paciente_nome,
    prontuario.descricao
FROM paciente
INNER JOIN prontuario
    ON paciente.paciente_cpf = prontuario.paciente_cpf;

DROP PROCEDURE IF EXISTS sp_relatorio_paciente;

DELIMITER $$

CREATE PROCEDURE sp_relatorio_paciente(
    IN p_paciente_cpf VARCHAR(11)
)
BEGIN
    SELECT
        paciente_nome,
        descricao
    FROM relatorio_paciente
    WHERE paciente_cpf = p_paciente_cpf;
END$$

DELIMITER ;

CREATE TABLE IF NOT EXISTS perfil_profissional (
    profissional_cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    formacao VARCHAR(200) NOT NULL,
    especialidades VARCHAR(300) NOT NULL,
    abordagem VARCHAR(150) NOT NULL,
    modalidade VARCHAR(50) NOT NULL,
    descricao VARCHAR(500),
    CONSTRAINT fk_perfil_profissional FOREIGN KEY (profissional_cpf) REFERENCES profissional(profissional_cpf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS preferencia_paciente (
    paciente_cpf VARCHAR(11) NOT NULL,
    interesse VARCHAR(100) NOT NULL,
    PRIMARY KEY (paciente_cpf, interesse),
    CONSTRAINT fk_preferencia_paciente FOREIGN KEY (paciente_cpf) REFERENCES paciente(paciente_cpf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS interesse_profissional (
    profissional_cpf VARCHAR(11) NOT NULL,
    interesse VARCHAR(100) NOT NULL,
    PRIMARY KEY (profissional_cpf, interesse),
    CONSTRAINT fk_interesse_profissional FOREIGN KEY (profissional_cpf) REFERENCES profissional(profissional_cpf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS agendamento_status (
    agendamento_id INT NOT NULL PRIMARY KEY,
    status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',
    CONSTRAINT fk_status_agendamento FOREIGN KEY (agendamento_id) REFERENCES agendamento(agendamento_id) ON DELETE CASCADE ON UPDATE CASCADE
);
