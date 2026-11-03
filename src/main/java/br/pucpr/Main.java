package br.pucpr;

import br.pucpr.table.Table;
import br.pucpr.table.model.ColumnTableData;
import br.pucpr.table.model.PagedTableData;
import br.pucpr.user.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var usuarios = new ColumnTableData<>(
            List.of(
                new User(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"),
                new User(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"),
                new User(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"),
                new User(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"),
                new User(105L, "Lucas Mendes", "lucas@email.com", "12345"),
                new User(106L, "", "beatriz@email.com", "55566677788")
            ),
            new IdColumn(), new CpfColumn(), new EmailColumn(), new NameColumn()
        );

        var pages = new PagedTableData(usuarios, 2);
        var userTable = new Table(pages);

        pages.addObserver(userTable::print);
        pages.addObserver(() -> System.out.println("Observador: dados da tabela atualizados."));

        System.out.println("TABELA INICIAL");
        userTable.print();

        System.out.println("MUDANÇA DE PÁGINA");
        pages.setPage(1);

        System.out.println("EDIÇÃO DE UM USUÁRIO");
        usuarios.update(2, new User(103L, "João Pedro Atualizado", "joao.novo@email.com", "45678912345"));

        System.out.println("ADICIONANDO NOVO USUÁRIO");
        usuarios.add(new User(107L, "Novo Usuário", "novo@email.com", "98765432101"));

        System.out.println("VOLTA À PÁGINA INICIAL");
        pages.setPage(0);
    }
}
