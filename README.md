# BlackBattleship

## Identificação do grupo

**Grupo 7**

| Curso | Número | Nome |
| :--- | :--- | :--- |
| LETI | 113146 | Ricardo Oliveira |
| LETI | 129360 | Francisco Farinha |
| LETI | 129763 | Rebeca Noronha |

## Product Backlog — Batalha Naval

Backlog proposto para o jogo de Batalha Naval do papergames.io, em português de Portugal.
A numeração identifica as User Stories e deve manter-se estável para permitir a sua
referência nos cenários de teste e nos commits.

### Fontes e âmbito

Páginas consultadas em **26 de novembro de 2026**:

- **[J]** [Batalha Naval: modos de jogo e regras](https://papergames.io/en/battleship).
- **[T]** [Criação de torneios de Batalha Naval](https://papergames.io/en/t/create-tournament?game=Battleships).
- **[M]** [Os meus torneios](https://papergames.io/en/t/my-tournaments).
- **[G]** [Organização e modalidades de torneios — Gruppo](https://papergames.io/en/pricing/gruppo).
- **[R]** [Torneio diário e classificação RANKA](https://papergames.io/en/blog/ranka).

A consulta incluiu o conteúdo público e uma inspeção da interface com Chrome e
Selenium. Confirmaram-se os campos de nome e descrição, a mudança de modalidade,
formato e duração na pré-visualização do torneio, o pedido de nickname e o acesso
restrito a «My tournaments». A entrada contra o robot chegou a uma ligação de
sala; a partida completa e a entrada de um amigo não ficaram validadas.

Esta inspeção é preparatória: a bateria de aceitação ainda terá de ser gravada,
implementada e executada. As opções efetivas podem depender da sessão, do plano
e das permissões. A disponibilidade de um formulário não comprova autorização
para criar ou iniciar o torneio.

### User Stories

**US01–US08: fonte [J].**

#### US01 — Jogar como convidado

Como visitante, quero jogar sem conta, para experimentar.

- **Aceitação:** numa sessão nova, é possível fornecer um nickname e chegar à sala de jogo sem autenticação por conta.

#### US02 — Jogar contra um robot

Como jogador, quero enfrentar um robot, para praticar.

- **Aceitação:** após selecionar «Play vs robot» e identificar o convidado, a sala carrega o tabuleiro e identifica o computador como adversário. Apenas mudar de URL não basta.

#### US03 — Convidar um amigo

Como jogador, quero convidar amigos por ligação, para jogarmos.

- **Aceitação:** «Play with a friend» disponibiliza uma ligação da sala. Ao abrir essa ligação numa segunda sessão independente, o amigo pode identificar-se e juntar-se à mesma sala.

#### US04 — Encontrar um adversário

Como jogador, quero encontrar adversários online, para competir.

- **Aceitação:** o modo online apresenta a procura de adversário e, havendo outro jogador disponível, estabelece a partida. A indisponibilidade de adversários não deve ser confundida com um defeito do jogo.

#### US05 — Consultar as regras

Como principiante, quero consultar as regras, para compreender o jogo.

- **Aceitação:** a secção de regras fica acessível e descreve tabuleiros 10 × 10, cinco navios e a condição de vitória. Este cenário valida a informação publicada; o tabuleiro interativo é validado nas histórias de partida.

#### US06 — Disparar por turnos

Como jogador, quero atacar por turnos, para afundar navios adversários.

- **Aceitação:** numa partida controlada, um acerto permite repetir e um tiro na água transfere a vez; a interface distingue os dois resultados. A escolha das próximas casas baseia-se no resultado visível, sem assumir posições fixas dos navios.

#### US07 — Usar armas especiais

Como jogador, quero usar armas especiais, para atingir várias casas.

- **Aceitação:** com um míssil grande disponível, um disparo numa casa interior produz o padrão de cinco casas em cruz e atualiza a disponibilidade da arma. O cenário não depende do aparecimento aleatório de presentes.

#### US08 — Vencer a partida

Como jogador, quero afundar a frota adversária, para vencer.

- **Aceitação:** ao afundar a última casa da frota adversária numa partida controlada, a interface apresenta a vitória ao vencedor e a derrota ao outro jogador.

#### US09 — Identificar um torneio

Como organizador, quero atribuir um nome ao torneio, para que os convidados o reconheçam.

- **Aceitação:** um nome preenchido aparece na pré-visualização; deixando-o vazio, a ação «Create and share» fica indisponível. Este cenário valida a configuração anterior à criação, sem publicar torneios.
- **Fonte:** [T].

#### US10 — Selecionar Batalha Naval para um torneio

Como organizador, quero escolher Batalha Naval, para organizar uma competição deste jogo.

- **Aceitação:** ao abrir a criação a partir de Batalha Naval, a opção correspondente fica selecionada e a pré-visualização identifica esse jogo.
- **Fontes:** [T], [G].

#### US11 — Escolher a modalidade do torneio

Como organizador, quero escolher uma modalidade disponível, para adequar a competição ao grupo.

- **Aceitação:** mudar entre «Leaderboard» e «Bracket» atualiza a seleção e a modalidade apresentada na pré-visualização; em «Bracket» são apresentadas as modalidades específicas disponíveis. Não é necessário criar o torneio para validar esta escolha.
- **Fonte:** [G].

#### US12 — Partilhar um torneio privado

Como organizador, quero partilhar uma ligação exclusiva, para convidar o meu grupo para o torneio.

- **Aceitação:** após a criação autorizada de um torneio com nome único, é disponibilizada uma ligação própria; uma segunda sessão consegue abrir o torneio através dela.
- **Fonte:** [T].

#### US13 — Iniciar um torneio

Como organizador, quero iniciar o torneio quando o grupo estiver pronto, para começar a competição.

- **Aceitação:** com um torneio criado, o organizador autorizado e os participantes necessários prontos, iniciar a competição altera o estado e permite o emparelhamento previsto na modalidade.
- **Fonte:** [G].

#### US14 — Consultar os meus torneios

Como organizador, quero consultar os meus torneios, para acompanhar as competições.

- **Aceitação:** sem autenticação, o acesso apresenta uma indicação de que é necessário iniciar sessão. Com uma conta autorizada, a listagem apresenta nome, modalidade, jogo, participantes, estado e data de criação, permitindo aceder à criação de torneios.
- **Fonte:** [M].

#### US15 — Percorrer a lista de torneios

Como organizador, quero consultar a lista por páginas, para encontrar torneios quando existirem muitos registos.

- **Aceitação:** com autenticação e mais registos do que o tamanho de página escolhido, mudar de página altera as linhas e o intervalo apresentado; alterar o tamanho da página respeita o total. Uma tabela vazia não demonstra que a paginação de resultados funciona.
- **Fonte:** [M].

#### US16 — Consultar a classificação diária

Como jogador, quero consultar a classificação diária, para acompanhar a competição atual.

- **Aceitação:** o painel diário apresenta a classificação e o tempo restante; havendo resultados, cada entrada identifica um jogador e uma pontuação. O teste não fixa nomes, posições, valores ou a hora exata do contador.
- **Fontes:** [J], [R]; painel observado na interface.

#### US17 — Acompanhar a classificação RANKA

Como jogador, quero acompanhar a classificação RANKA, para avaliar o meu desempenho ao longo do tempo.

- **Aceitação:** o painel RANKA apresenta as pontuações disponíveis e a ligação «Learn more» abre a explicação do período de 30 dias. A consulta não exige esperar que termine um torneio nem comprova o cálculo histórico de pontos.
- **Fontes:** [J], [R]; painel observado na interface.

#### US18 — Descrever um torneio

Como organizador, quero acrescentar uma descrição opcional, para explicar o torneio aos convidados.

- **Aceitação:** «Add a description» revela o campo; o texto introduzido aparece na pré-visualização e o contador acompanha o comprimento. O campo pode ficar vazio.
- **Fonte:** [T], formulário observado na interface.

#### US19 — Escolher o formato das partidas

Como organizador, quero escolher quantas partidas decidem uma ronda, para ajustar o formato da competição.

- **Aceitação:** em «Customize», selecionar «Best of 5» altera a seleção e o resumo «FORMAT» para esse formato, sem criar o torneio.
- **Fonte:** [T], alteração confirmada na interface.

#### US20 — Definir o tempo por jogador

Como organizador, quero definir o tempo disponível por jogador, para controlar a duração das partidas.

- **Aceitação:** em «Customize», selecionar dois minutos altera a seleção e o resumo «TIME / PLAYER» para dois minutos, sem criar o torneio.
- **Fonte:** [T], alteração confirmada na interface.

#### US21 — Escolher um nickname

Como visitante, quero escolher um nickname, para me identificar ao entrar no jogo.

- **Aceitação:** numa sessão nova, escolher um modo de jogo apresenta o pedido de nickname; «Continue» está desativado com o campo vazio e fica disponível após introduzir um nickname aceite.
- **Fonte:** [J], comportamento confirmado na interface; também previsto em B.1 do guião.

### Cenários para gravação e implementação

Os cenários abaixo especificam preparação, ação e resultado observável. «Direto»
significa que não depende de concluir uma partida ou criar torneios; a bateria
formal continua por implementar. «Condicionado» identifica os dados ou serviços
adicionais necessários. Os resultados devem ser verificados na interface.

| US | Preparação e ação principal | Resultado a verificar | Execução |
| --- | --- | --- | --- |
| US01 | Sessão nova; escolher robot, indicar nickname e continuar. | Entrada como convidado na sala, sem autenticação por conta. | Requer carregamento do serviço de salas. |
| US02 | Convidado identificado; iniciar contra o robot. | Tabuleiro carregado e adversário computador identificado. | Requer serviço de jogo e gestão de eventuais anúncios. |
| US03 | Duas sessões isoladas; a primeira gera o convite e a segunda abre a ligação. | Ambos os jogadores identificados na mesma sala. | Condicionado: dois browsers/sessões e serviço de salas. |
| US04 | Convidado identificado; iniciar a procura online. | Procura visível e partida estabelecida quando há adversário. | Condicionado: disponibilidade de outro jogador. |
| US05 | Abrir Batalha Naval e deslocar a página até às regras. | Secção visível com dimensão, frota e condição de vitória. | Direto. |
| US06 | Partida entre duas sessões controladas; disparar e observar resultados. | Acerto conserva a vez; água transfere-a. | Condicionado: obter os dois resultados durante a partida. |
| US07 | Partida com míssil grande disponível; selecionar arma e casa interior. | Área em cruz e atualização da disponibilidade. | Condicionado: estado de partida adequado. |
| US08 | Partida controlada; continuar até afundar a última casa da frota. | Resultado final coerente nas duas sessões. | Condicionado: partida completa; duração elevada. |
| US09 | Abrir criação; escrever `Lab5-Nome`; verificar resumo; apagar o nome. | Resumo acompanha o nome e a criação fica desativada sem ele. | Direto; não submeter. |
| US10 | A partir da página de Batalha Naval, abrir a criação de torneios. | Batalha Naval selecionada e identificada no resumo. | Direto. |
| US11 | Na criação, selecionar «Bracket» e regressar a «Leaderboard». | Seleção e resumo mudam em ambos os sentidos. | Direto; não submeter. |
| US12 | Conta com permissão; criar torneio de nome único e abrir convite noutra sessão. | Ligação resolve para o torneio correto. | Condicionado: criação autorizada e posterior limpeza dos dados. |
| US13 | Organizador autenticado; torneio com participantes suficientes; iniciar. | Estado e emparelhamentos atualizados. | Condicionado: modalidade, permissões e participantes. |
| US14 | Abrir «My tournaments» primeiro sem conta e depois com conta autorizada. | Pedido de autenticação; após login, tabela ou estado vazio adequado. | Acesso sem conta observado; caso positivo exige conta. |
| US15 | Conta com torneios suficientes; escolher tamanho de página e avançar. | Linhas, intervalo e total coerentes. | Condicionado: conta e dados previamente preparados. |
| US16 | Abrir o painel diário e aguardar pelo carregamento. | Classificação e contador, com dados coerentes quando disponíveis. | Direto; não fixar dados que mudam diariamente. |
| US17 | Consultar RANKA e abrir «Learn more». | Painel disponível e explicação do período de classificação. | Direto; não validar acumulação de pontos ao longo de 30 dias. |
| US18 | Abrir descrição; escrever `Torneio do grupo 7`. | Mesmo texto no resumo e contador correspondente. | Direto; não submeter. |
| US19 | Abrir «Customize»; selecionar «Best of 5». | Opção selecionada e resumo «FORMAT» atualizado. | Direto; não submeter. |
| US20 | Abrir «Customize»; escolher dois minutos por jogador. | Opção selecionada e resumo «TIME / PLAYER» atualizado. | Direto; não submeter. |
| US21 | Sessão nova; abrir pedido de nickname e preencher `Lab5Jogador`. | «Continue» desativado antes e disponível depois do preenchimento. | Direto; não exige concluir a entrada na sala. |



## Testes da suite TestSuite_129360

Os quatro cenários de `TestSuite_129360.side` estão convertidos para Selenium com
JUnit 5 e Page Object Model em `src/test/java/iscteiul/ista/blackbattleship/`:

| Cenário no SIDE | Page Object (seletores e operações) | Page Test (JUnit e assertions) |
| --- | --- | --- |
| `UserStoryTest1` | `UserStory1.java` | `UserStory1Test.java` |
| `UserStoryTest9` | `UserStory9.java` | `UserStory9Test.java` |
| `UserStoryTest10` | `UserStory10.java` | `UserStory10Test.java` |
| `UserStoryTest17` | `UserStory17.java` | `UserStory17Test.java` |
