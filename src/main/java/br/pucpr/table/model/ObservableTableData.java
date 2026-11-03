package br.pucpr.table.model;

public interface ObservableTableData extends TableData {
    void addObserver(DataChangedObserver observer);
    void removeObserver(DataChangedObserver observer);
}
