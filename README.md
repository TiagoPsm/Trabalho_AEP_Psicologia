Requisitos identificados para esse projeto ----------------------------------------

RF01 - Sistema de agendamento

	Origem: Na entrevista feita com a profissional de psicologia, é relatada a falta de uma plataforma centralizada para à ajudar com sua agenda
	Descrição: A plataforma deve permitir agendar, remarcar e cancelar consultas (presenciais e online), e envios de lembretes automáticos ao paciente e profissional
	Regra de negócio: O agendamento deve verificar a disponibilidade do profissional, não permitindo um dois agendamentos em um mesmo horário e dia

RF02 - Recomendação inteligente de psicólogo

	Origem: Dificuldade de encontrar um profissional adequado 
	Descrição: Criar “tags” para juntar pacientes e profissionais com interesses em comum
	Regra de negócio: O sistema tem que permitir que o paciente informe seus itens de interesse, necessidades, preferências e outras características que deseja ao se encontrar com um psicólogo para terapia. A 			plataforma deve utilizar essas informações, juntamente com a abordagem, especialidades e perfil dos psicólogos, para recomendar profissionais com maior compatibilidade com o paciente. 

RF03 - Painel de gestão para o psicólogo

	Origem: Foi notada, pela profissional, a falta de uma visão consolidada sobre seus pacientes, frequência, cancelamento e faturamento
	Descrição: A plataforma deve ter um painel com indicadores como o número de consultas, taxa de comparecimento, cancelamentos e faturamento.
  	Regra de negócio: Os dados devem ser mantidos em sigilo e devem ser visualizados apenas para o devido profissional

RF04 - Busca e filtro de psicólogos

	Origem: Necessidade de facilitar o encontro entre pacientes e profissionais 
	Descrição: O sistema deve permitir que o paciente pesquise e filtre psicólogos cadastrados na plataforma de acordo com critérios como área de atuação, abordagem psicológica, modalidade de atendimento, 				disponibilidade e nível de compatibilidade. 
	Regra de negócio: O sistema deve apresentar apenas psicólogos com cadastro profissional válido e que atendam aos critérios selecionados pelo paciente. Os resultados devem ser organizados de acordo com o nível 		de compatibilidade entre o perfil do paciente e o profissional, priorizando aqueles que apresentarem maior afinidade. 

RF05 - Suporte à divulgação e visibilidade do psicólogo

	Origem: Na entrevista, foi relatada a frustração com a dificuldade de se divulgar e encontrar pacientes
	Descrição: A plataforma deve oferecer um perfil público para o psicólogo, com suas especialidades, formação, valores, e gostos pessoais (que ele próprio irá colocar)
	Regra de negócio: O perfil deve poder ser criado, atualizado e excluído.

Cronograma do desenvolvimento ----------------------------------------

Sprint / Data	Épico	UserStory	Responsável

Sprint 1 / (31/08 a 06/09)	Cadastro e Perfis	COMO UM psicólogo EU QUERO criar e gerenciar meu perfil profissional na plataforma PARA QUE eu possa divulgar minhas especialidades, abordagem e informações de contato.	Tiago

Sprint 2 (07/09 a 13/09)	Cadastro e Perfis	COMO UM paciente EU QUERO me cadastrar na plataforma e informar minhas preferências, interesses e necessidades PARA QUE o sistema possa me recomendar profissionais compatíveis.	Bárbara

Sprint 3 (14/09 a 20/09)	Recomendação Inteligente	COMO UM paciente EU QUERO receber sugestões de psicólogos com base nas minhas preferências e perfil PARA QUE eu encontre um profissional com maior afinidade e me sinta mais confortável.	Maria

Sprint 4 (21/09 a 27/09)	Busca e Filtros	COMO UM paciente EU QUERO filtrar psicólogos por abordagem, modalidade de atendimento, disponibilidade e localização PARA QUE eu encontre profissionais que atendam aos meus critérios específicos.
Tiago e Bárbara 

Sprint 5 (28/09 a 04/10)	Agendamento	COMO UM paciente EU QUERO agendar, remarcar e cancelar consultas com um psicólogo PARA QUE eu possa gerenciar minhas sessões de forma prática e integrada.	Maria

Sprint 6 (05/10 a 11/10)	Agendamento	COMO UM psicólogo EU QUERO visualizar minha agenda de consultas e receber notificações de novos agendamentos ou cancelamentos PARA QUE eu possa organizar minha rotina de atendimentos.	Tiago

Sprint 7 (12/10 a 18/10)	Painel de Gestão	COMO UM psicólogo EU QUERO acessar um painel com indicadores de frequência, cancelamentos e faturamento PARA QUE eu tenha uma visão consolidada da minha prática clínica.	Bárbara
