# Equipe
- Thiago dos Santos Araujo
- João Gabriel Costa
- Cosme Soares de Andrade Neto

# Relatório: O Problema do Jantar dos Filósofos

## 1. Introdução

O **Jantar dos Filósofos** é um problema clássico de sincronização na comunicação entre processos e threads em sistemas operacionais, proposto por **Edsger W. Dijkstra** em 1965. Este problema ilustra de forma elegante os desafios relacionados ao compartilhamento de recursos limitados em sistemas concorrentes.

---

## 2. Descrição do Problema

### 2.1 Cenário

O problema consiste em cinco filósofos sentados em uma mesa redonda para jantar. Cada filósofo possui:

- Um prato com espaguete à sua frente
- Acesso a um garfo posicionado entre ele e seus vizinhos

### 2.2 Regras e Comportamento

- O espaguete é muito escorregadio, exigindo **dois garfos** para ser consumido.
- Cada filósofo alterna entre duas atividades: **comer** ou **pensar**.
- Quando um filósofo fica com fome, ele tenta pegar os garfos à sua esquerda e direita (um de cada vez, em qualquer ordem).
- Após conseguir dois garfos, o filósofo come por um tempo determinado.
- Depois de comer, ele recoloca os garfos na mesa e volta a pensar.

### 2.3 Algoritmo Simples Proposto

1. Tentar pegar um garfo qualquer
2. Se não conseguir, aguardar
3. Tentar pegar o segundo garfo
4. Com ambos os garfos, começar a comer
5. Ao final, devolver um garfo e depois o outro

---

## 3. Problemas Identificados

### 3.1 Deadlock / Impasse (Solução 1)

- **Problema:** Se todos os filósofos pegarem simultaneamente um garfo, todos ficarão esperando indefinidamente pelo segundo garfo, gerando um **deadlock**.
- **Consequência:** O sistema trava completamente, pois nenhum filósofo consegue prosseguir.

### 3.2 Starvation / Morrer de Fome (Solução 2)

- **Cenário:** Implementação onde o filósofo que não consegue pegar o segundo garfo devolve o primeiro e aguarda um tempo fixo antes de tentar novamente.
- **Problema:** Um filósofo pode ficar tentando indefinidamente a mesma operação (pegar e devolver garfo) sem nunca conseguir comer, resultando em **starvation**.
- **Consequência:** Embora não haja travamento do sistema, alguns filósofos podem nunca conseguir se alimentar.

---

## 4. Solução com Semáforos (Solução 4)

### 4.1 Controle Individual dos Garfos

- Cada garfo é controlado por um **semáforo com apenas uma permissão**
- Garante **exclusão mútua**, impedindo que dois filósofos usem o mesmo garfo simultaneamente
- **Limitação:** Ainda pode causar deadlock se todos tentarem pegar o garfo esquerdo ao mesmo tempo

### 4.2 Semáforo Global

**Implementação da Solução:**

- Utiliza um **semáforo global com quatro permissões**
- Antes de tentar pegar qualquer garfo, o filósofo deve adquirir uma permissão do semáforo global

**Resultado:**

- No máximo quatro filósofos podem tentar comer simultaneamente

### 4.3 Vantagens da Solução com Semáforo Global

- **Prevenção de Deadlock:** Com cinco filósofos e apenas quatro permissões, sempre haverá pelo menos um filósofo apenas pensando
- **Quebra do Ciclo de Espera:** Elimina a possibilidade de espera circular necessária para o deadlock
- **Funcionamento Equilibrado:** Os filósofos se revezam para comer de forma justa
- **Sistema Seguro:** Evita travamento total do sistema

---

## 5. Estrutura de Implementação

O documento menciona a implementação através de classes:

- `Filosofo`: Representa o comportamento individual de cada filósofo
- `Mesa`: Gerencia o ambiente e recursos compartilhados
- `Semaforo`: Implementa o mecanismo de sincronização
- `Main`: Coordena a execução do programa

---

## 6. Conclusão

O **Problema do Jantar dos Filósofos** demonstra de forma clara os desafios da programação concorrente e a importância de mecanismos adequados de sincronização. A solução com semáforos apresentada oferece uma abordagem eficaz que:

- Evita **deadlocks** através da limitação controlada de recursos
- Previne **starvation** garantindo que todos os filósofos tenham oportunidade de comer
- Mantém o sistema funcionando de forma **equilibrada** e **justa**

Este problema continua sendo fundamental no estudo de sistemas operacionais, servindo como base para compreender conceitos essenciais como **exclusão mútua**, **sincronização de processos** e **gerenciamento de recursos compartilhados**.

---

## 7. Referências

- https://blog.pantuza.com/artigos/o-jantar-dos-filosofos-problema-de-sincronizacao-em-sistemas-operacionais
- https://deinfo.uepg.br/~alunoso/2021/SO/Filosofos_python/
