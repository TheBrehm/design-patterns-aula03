package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class ColumnTableData<T> implements ObservableTableData {
    private final List<T> records;
    private final List<ColumnData<? super T>> columns;
    private final List<DataChangedObserver> observers = new ArrayList<>();

    public ColumnTableData(Collection<? extends T> records, Collection<? extends ColumnData<? super T>> columns) {
        this.records = new ArrayList<>(records);
        this.columns = new ArrayList<>(columns);
    }

    @SafeVarargs
    public ColumnTableData(Collection<? extends T> records, ColumnData<? super T>... columns) {
        this(records, Arrays.asList(columns));
    }

    @Override
    public int rowCount() {
        return records.size();
    }

    @Override
    public int colCount() {
        return columns.size();
    }

    @Override
    public String header(int col) {
        return columns.get(col).header();
    }

    @Override
    public String get(int row, int col) {
        return columns.get(col).get(records.get(row));
    }

    public void add(T record) {
        records.add(record);
        notifyObservers();
    }

    public void update(int index, T record) {
        records.set(index, record);
        notifyObservers();
    }

    public T remove(int index) {
        T removed = records.remove(index);
        notifyObservers();
        return removed;
    }

    @Override
    public void addObserver(DataChangedObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    @Override
    public void removeObserver(DataChangedObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (var observer : List.copyOf(observers)) {
            observer.onTableChange();
        }
    }
}
