package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public final class IdColumn implements ColumnData<User> {
    @Override
    public String header() {
        return "  ID";
    }

    @Override
    public String get(User user) {
        return user.id() == null ? "-" : "%4d".formatted(user.id());
    }
}
