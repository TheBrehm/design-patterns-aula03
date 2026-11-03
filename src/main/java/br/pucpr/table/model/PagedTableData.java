package br.pucpr.table.model;

import br.pucpr.table.TableData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PagedTableData implements ObservableTableData {
    private final TableData original;
    private final int pageSize;
    private final List<DataChangedObserver> observers = new ArrayList<>();
    private int page;

    public PagedTableData(TableData original, int pageSize) {
        if (original == null) {
            throw new IllegalArgumentException("Dados não podem ser nulos");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("O tamanho da página deve ser maior que zero");
        }
        this.original = original;
        this.pageSize = pageSize;
        if (original instanceof ObservableTableData observable) {
            observable.addObserver(this::onOriginalChanged);
        }
    }

    private void onOriginalChanged() {
        if (pageCount() == 0) {
            page = 0;
        } else if (page >= pageCount()) {
            page = pageCount() - 1;
        }
        notifyObservers();
    }

    public int pageCount() {
        return (original.rowCount() + pageSize - 1) / pageSize;
    }

    public int currentPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 0 || page >= pageCount()) {
            throw new IndexOutOfBoundsException("Página inválida: " + page);
        }
        if (this.page != page) {
            this.page = page;
            notifyObservers();
        }
    }

    @Override
    public int rowCount() {
        return Math.max(0, Math.min(pageSize, original.rowCount() - page * pageSize));
    }

    @Override
    public int colCount() {
        return original.colCount();
    }

    @Override
    public String header(int col) {
        return original.header(col);
    }

    @Override
    public String get(int row, int col) {
        if (row < 0 || row >= rowCount()) {
            throw new IndexOutOfBoundsException("Linha inválida: " + row);
        }
        return original.get(page * pageSize + row, col);
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
