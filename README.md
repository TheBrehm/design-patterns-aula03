# Atividade 02: Paginação

Solução de paginação genérica usando o padrão Decorator.

## Requisitos atendidos

- Aplica-se a qualquer implementação de `TableData`.
- Não altera nem estende a classe `Table`.
- Pode ser aplicada a futuras implementações de `TableData`.
- Página inicial 0; navegação com `setPage(int)`.

## Execução

Java 21. Execute `br.pucpr.Main` no IntelliJ IDEA ou `gradlew.bat run`.

O programa mostra a tabela de usuários em três páginas e também uma versão paginada da tabela de planetas.
