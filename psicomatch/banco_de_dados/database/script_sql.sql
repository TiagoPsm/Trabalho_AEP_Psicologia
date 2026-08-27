
CREATE DATABASE trabalho_psicologia;

USE trabalho_psicologia;


CREATE TABLE profissional (
	profissional_cpf VARCHAR(11) NOT NULL PRIMARY KEY,
	profissional_nome VARCHAR(100) NOT NULL,
	profissional_telefone VARCHAR(40) NOT NULL UNIQUE,
	profissional_email VARCHAR(150) UNIQUE,
	profissional_valor_consulta DECIMAL(10,2) NOT NULL
);

CREATE TABLE paciente (
	paciente_cpf VARCHAR(11) PRIMARY KEY,
	paciente_nome VARCHAR(100) NOT NULL,
	paciente_telefone VARCHAR(40) NOT NULL,
	paciente_endereco VARCHAR(250) NOT NULL,
	profissional_cpf VARCHAR(11),

	CONSTRAINT fk_paciente_profissional
	FOREIGN KEY (profissional_cpf)
	REFERENCES profissional (profissional_cpf)
);

CREATE TABLE prontuario (
	prontuario_id INT PRIMARY KEY,
	paciente_cpf VARCHAR (11) NOT NULL,
 	profissional_cpf VARCHAR (11) NOT NULL,

	descricao VARCHAR(500),

	CONSTRAINT fk_prontuario_paciente
        FOREIGN KEY (paciente_cpf)
        REFERENCES paciente (paciente_cpf),
    
   	CONSTRAINT fk_prontuario_profissional
        FOREIGN KEY (profissional_cpf)
        REFERENCES profissional (profissional_cpf)
);

CREATE TABLE clinica (
	clinica_cnpj VARCHAR(14) NOT NULL PRIMARY KEY,
	clinica_nome VARCHAR(50) NOT NULL,
	clinica_rua VARCHAR(100) NOT NULL, 
	clinica_bairro VARCHAR(40) NOT NULL,
	clinica_complemento VARCHAR(150),
	clinica_numero VARCHAR(250) NOT NULL

);

CREATE TABLE agendamento (
	agendamento_id INT PRIMARY KEY,
	agendamento_data DATE NOT NULL,
	agendamento_horario TIME NOT NULL,
	clinica_cnpj VARCHAR(14) NOT NULL,
	profissional_cpf VARCHAR(11) NOT NULL,
	paciente_cpf VARCHAR(11) NOT NULL,

	CONSTRAINT fk_agendamento_paciente
	FOREIGN KEY (paciente_cpf)
	REFERENCES paciente (paciente_cpf),

	CONSTRAINT fk_agendamento_profissional
	FOREIGN KEY (profissional_cpf)
	REFERENCES profissional (profissional_cpf),

	CONSTRAINT fk_agendamento_clinica
	FOREIGN KEY (clinica_cnpj)
	REFERENCES clinica (clinica_cnpj)
);

CREATE TABLE equipe (
	clinica_cnpj VARCHAR(14),
	profissional_cpf VARCHAR(11),

	PRIMARY KEY (clinica_cnpj, profissional_cpf),

	CONSTRAINT fk_equipe_clinica 
	FOREIGN KEY (clinica_cnpj) 
	REFERENCES clinica (clinica_cnpj),

    	CONSTRAINT fk_equipe_profissional 
	FOREIGN KEY (profissional_cpf) 
	REFERENCES profissional (profissional_cpf)
);

CREATE TABLE log_paciente (
	log_id INT AUTO_INCREMENT PRIMARY KEY,
	log_alterado_de_paciente_cpf VARCHAR(11) NOT NULL,
	log_alterado_para_paciente_cpf VARCHAR(11) NOT NULL,
	log_alterado_de_paciente_nome VARCHAR(100) NOT NULL,
	log_alterado_para_paciente_nome VARCHAR(100) NOT NULL,
	log_alterado_de_paciente_telefone VARCHAR (40) NOT NULL,
	log_alterado_para_paciente_telefone VARCHAR (40) NOT NULL,
	log_data DATETIME NOT NULL,
	log_alterado_de_profissional_cpf VARCHAR(11) NOT NULL,
	log_alterado_para_profissional_cpf VARCHAR(11) NOT NULL,
	log_usuario VARCHAR(30) NOT NULL,
	log_operacao VARCHAR(30) NOT NULL
	
)