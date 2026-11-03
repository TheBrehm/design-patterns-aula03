# Atividade 03: Notificação de mudanças

Implementação do padrão Observer para atualizar a saída de uma tabela ou outros componentes quando seus dados mudam.

## Componentes

- `DataChangedObserver`: contrato do observador.
- `ObservableTableData`: dados de tabela que permitem inscrição/cancelamento de observadores.
- `ColumnTableData<T>`: notifica ao adicionar, atualizar ou remover registros.
- `PagedTableData`: notifica ao trocar de página e propaga alterações da tabela original.
- `Main`: demonstra a atualização automática com `userTable::print` e um segundo observador.

A classe `Table` permanece inalterada.

## Execução

Java 21. Execute `br.pucpr.Main` no IntelliJ IDEA ou `gradlew.bat run` no Windows.
