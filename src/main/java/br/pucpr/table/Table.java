package br.pucpr.table;

import java.util.ArrayList;

public final class Table {

    private TableData data;
    private Theme theme;
    private boolean alignRight;

    public Table(TableData data, Theme theme, boolean alignRight) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        this.data = data;
        setTheme(theme);
        this.alignRight = alignRight;
    }

    public Table(TableData data, Theme theme) {
        this(data, theme, false);
    }

    public Table(TableData data) {
        this(data, Theme.NORMAL);
    }

    public TableData getData() {
        return data;
    }

    public void setData(TableData data) {
        this.data = data;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        if (theme == null) {
            throw new IllegalArgumentException("Theme cannot be null");
        }

        this.theme = theme;
    }

    public boolean isAlignRight() {
        return alignRight;
    }

    public void setAlignRight(boolean alignRight) {
        this.alignRight = alignRight;
    }

    public void print() {
        System.out.print(this);
    }

    private String headerLine() {
        final var sb = new StringBuilder();

        sb.append("|");

        for (int i = 0; i < data.colCount(); i++) {
            sb.append(String.format(" %s |", data.header(i)));
        }

        return sb.toString();
    }

    private String lineFormat() {
        final var sb = new StringBuilder();

        sb.append("|");

        for (int i = 0; i < data.colCount(); i++) {
            final var size = data.header(i).length();

            sb.append(" %-")
                    .append(size)
                    .append("s |");
        }

        return sb.toString();
    }

    private String trimmedData(int row, int col) {
        final var colSize = data.header(col).length();
        final var value = data.get(row, col);

        if (value.length() <= colSize) {
            return value;
        }

        if (colSize < 3) {
            return ".".repeat(colSize);
        }

        return value.substring(0, colSize - 3) + "...";
    }

    @Override
    public String toString() {
        final var lines =
                new ArrayList<String>(data.rowCount() + 3);

        final var headerLine = headerLine();

        final var borderLine =
                theme.getBorderChar().repeat(headerLine.length());

        lines.add(borderLine);
        lines.add(headerLine);
        lines.add(borderLine);

        for (int row = 0; row < data.rowCount(); row++) {
            final var rowData =
                    new String[data.colCount()];

            for (int col = 0; col < data.colCount(); col++) {
                rowData[col] = trimmedData(row, col);
            }

            lines.add(
                    String.format(
                            lineFormat(),
                            (Object[]) rowData
                    )
            );
        }

        lines.add(borderLine);

        final var spacing =
                alignRight ? "                    " : "";

        return lines.stream()
                .reduce(
                        "",
                        (a, b) -> a + spacing + b + "\n"
                );
    }
}