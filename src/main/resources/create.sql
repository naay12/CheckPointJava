CREATE TABLE IF NOT EXISTS PACIENTES (
idPaciente int auto_increment primary key,
nome varchar(100) NOT NULL,
sobrenome varchar(100) NOT NULL,
telefone varchar(20),
endereco int,
cpf varchar(100) NOT NULL,
dataCadastro varchar(20)
);

CREATE TABLE IF NOT EXISTS DENTISTAS (
idDentista INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
sobrenome VARCHAR(100) NOT NULL,
matricula VARCHAR(10) NOT NULL);

CREATE TABLE IF NOT EXISTS ENDERECOS (
idEndereco INT AUTO_INCREMENT PRIMARY KEY,
estado VARCHAR(2),
cidade VARCHAR(50),
bairro VARCHAR(50)
cep VARCHAR(8),
rua VARCHAR(80),
numero VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS CONSULTAS (
idConsulta INT AUTO_INCREMENT PRIMARY KEY,
idDentista INT NOT NULL,
idPaciente INT NOT NULL,
dataHorario VARCHAR(50) NOT NULL
);
 
