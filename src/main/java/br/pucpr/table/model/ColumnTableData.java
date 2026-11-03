package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class ColumnTableData<T> implements TableData {
    private final List<T> records;
    private final List<ColumnData<? super T>> columns;

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
}
