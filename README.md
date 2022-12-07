# Check Point Java 
#### Projeto integrador metéria backend do curso Certified Tech Developer da Digital House.

##  Integrantes da equipe EQUIPE 5
> * Natali de Souza <br>
> * Anderson Silva <br>
> * Adriel Reis <br>
> * Wagner Morais


# Postman
## Cadastro de dentista
METODO: POST <br>
ROTA: http://localhost:8080/dentista 

{

        "nome" : "Natali",
        "sobrenome" : "Souza",
        "matricula":"12345"
},
{

        "nome" : "Anderson",
        "sobrenome" : "Silva",
        "matricula":"54321"
},
{

        "nome" : "Adriel",
        "sobrenome" : "Reis",
        "matricula":"56789"
},
{

        "nome" : "Wagner",
        "sobrenome" : "Morais",
        "matricula":"98765"
}
## Cadastro de paciente
METODO: POST <br>
ROTA: http://localhost:8080/paciente

{

        "nome" : "Natali",
        "sobrenome" : "Souza",
        "telefone":"11992345678",
        "cpf":"12345678910",
        "dataCadastro":"2022-12-07"
},
{

        "nome" : "Anderson",
        "sobrenome" : "Silva",
        "telefone":"19975345678",
        "cpf":"54345678910",
        "dataCadastro":"2022-12-09"
},
{

        "nome" : "Adriel",
        "sobrenome" : "Reis",
        "telefone":"21983345678",
        "cpf":"87645678910",
        "dataCadastro":"2022-12-08"
},
{

        "nome" : "Wagner",
        "sobrenome" : "Morais",
        "telefone":"31912345678",
        "cpf":"98745678910",
        "dataCadastro":"2022-12-06"
}
## Cadastro de endereço
METODO: POST <br>
ROTA: http://localhost:8080/endereco

{

        "estado": "SP",
        "cidade": "Sumaré",
        "bairro": "Real Park Sumaré",
        "cep": "13178574",
        "rua": "Avenida Cabo Pedro Hoffman",
        "numero": "235"
},
{

        "estado": "RJ",
        "cidade": "Cabo Frio",
        "bairro": "centro",
        "cep": "28910280",
        "rua": "Rua Belém",
        "numero": "10"
},
{

        "estado": "MG",
        "cidade": "Boa Esperança",
        "bairro": "centro",
        "cep": "37170000",
        "rua": "Rua Governador Valadares",
        "numero": "335"
},
{

        "estado": "PR",
        "cidade": "Curitiba",
        "bairro": "centro",
        "cep": "80240210",
        "rua": "Avenida República Argentina",
        "numero": "824"
}