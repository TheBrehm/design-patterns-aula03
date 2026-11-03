package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public final class NameColumn implements ColumnData<User> {
    @Override
    public String header() {
        return "          NOME           ";
    }

    @Override
    public String get(User user) {
        String name = user.name();
        return name == null || name.isBlank() ? "NÃO INFORMADO" : name;
    }
}
