package br.pucpr;

import br.pucpr.planet.Planet;
import br.pucpr.planet.PlanetsTableData;
import br.pucpr.table.Table;
import br.pucpr.table.model.ColumnTableData;
import br.pucpr.table.model.PagedTableData;
import br.pucpr.user.*;
import java.util.ArrayList;
import java.util.List;
import static br.pucpr.planet.PlanetType.*;

public class Main {
    public static void main(String[] args) {
        List<User> usuarios = List.of(
            new User(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"),
            new User(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"),
            new User(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"),
            new User(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"),
            new User(105L, "Lucas Mendes", "lucas@email.com", "12345"),
            new User(106L, "", "beatriz@email.com", "55566677788")
        );

        var data = new ColumnTableData<>(usuarios, new IdColumn(), new CpfColumn(), new EmailColumn(), new NameColumn());
        var pages = new PagedTableData(data, 2);
        var userTable = new Table(pages);

        System.out.println("PAGINAÇÃO DE USUÁRIOS");
        for (int p = 0; p < pages.pageCount(); p++) {
            pages.setPage(p);
            System.out.println("Página " + (p + 1) + " de " + pages.pageCount());
            userTable.print();
        }

        var planetas = new ArrayList<Planet>();
        planetas.add(new Planet("Mercúrio", 4879, 57_910_000L, ROCK));
        planetas.add(new Planet("Vênus", 12104, 108_200_000L, ROCK));
        planetas.add(new Planet("Terra", 12756, 149_600_000L, ROCK));
        planetas.add(new Planet("Marte", 6792, 227_940_000L, ROCK));
        planetas.add(new Planet("Júpiter", 142984, 778_330_000L, GAS));
        planetas.add(new Planet("Saturno", 120536, 1_429_400_000L, GAS));
        planetas.add(new Planet("Urano", 51118, 2_870_990_000L, ICE));
        planetas.add(new Planet("Netuno", 49528, 4_504_300_000L, ICE));
        planetas.add(new Planet("Plutão", 2376, 5_906_380_000L, DWARF));

        var planetPages = new PagedTableData(new PlanetsTableData(planetas), 4);
        var planetTable = new Table(planetPages);
        System.out.println("PAGINAÇÃO DE PLANETAS");
        for (int p = 0; p < planetPages.pageCount(); p++) {
            planetPages.setPage(p);
            System.out.println("Página " + (p + 1) + " de " + planetPages.pageCount());
            planetTable.print();
        }
    }
}
