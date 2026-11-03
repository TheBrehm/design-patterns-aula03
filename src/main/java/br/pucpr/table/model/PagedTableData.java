package br.pucpr.table.model;

import br.pucpr.table.TableData;

public final class PagedTableData implements TableData {
    private final TableData original;
    private final int pageSize;
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
        this.page = page;
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
}
