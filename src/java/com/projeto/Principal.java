package com.seuprojeto;

import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
    private static AutomovelDAO dao = new AutomovelDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    incluirAutomovel();
                    break;
                case 2:
                    removerAutomovel();
                    break;
                case 3:
                    alterarAutomovel();
                    break;
                case 4:
                    consultarAutomovel();
                    break;
                case 5:
                    listarAutomoveis();
                    break;
                case 6:
                    dao.salvarDados();
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        } while (opcao != 6);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Gerenciamento de Automóveis ---");
        System.out.println("1 - Incluir automóvel");
        System.out.println("2 - Remover automóvel");
        System.out.println("3 - Alterar dados de automóvel");
        System.out.println("4 - Consultar automóvel por placa");
        System.out.println("5 - Listar automóveis (ordenado)");
        System.out.println("6 - Salvar e sair");
        System.out.println("-----------------------------------");
    }

    private static void incluirAutomovel() {
        System.out.println("\n--- Incluir Automóvel ---");
        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim();
        if (placa.isEmpty()) {
            System.out.println("Placa não pode ser vazia.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();
        if (modelo.isEmpty()) {
            System.out.println("Modelo não pode ser vazio.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();
        if (marca.isEmpty()) {
            System.out.println("Marca não pode ser vazia.");
            return;
        }

        System.out.print("Ano: ");
        int ano = lerInteiro();
        if (ano <= 0) {
            System.out.println("Ano inválido.");
            return;
        }

        System.out.print("Valor: ");
        double valor = lerDouble();
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return;
        }

        Automovel novoAutomovel = new Automovel(placa, modelo, marca, ano, valor);
        dao.incluirAutomovel(novoAutomovel);
    }

    private static void removerAutomovel() {
        System.out.println("\n--- Remover Automóvel ---");
        System.out.print("Digite a placa do automóvel a ser removido: ");
        String placa = scanner.nextLine().trim();
        if (placa.isEmpty()) {
            System.out.println("Placa não pode ser vazia.");
            return;
        }
        dao.removerAutomovel(placa);
    }

    private static void alterarAutomovel() {
        System.out.println("\n--- Alterar Dados de Automóvel ---");
        System.out.print("Digite a placa do automóvel a ser alterado: ");
        String placa = scanner.nextLine().trim();
        if (placa.isEmpty()) {
            System.out.println("Placa não pode ser vazia.");
            return;
        }

        Automovel automovelExistente = dao.buscarAutomovelPorPlaca(placa);
        if (automovelExistente == null) {
            System.out.println("Automóvel com placa " + placa + " não encontrado.");
            return;
        }

        System.out.println("Automóvel encontrado: " + automovelExistente);
        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

        System.out.print("Novo Modelo (" + automovelExistente.getModelo() + "): ");
        String novoModelo = scanner.nextLine().trim();
        if (novoModelo.isEmpty()) {
            novoModelo = automovelExistente.getModelo();
        }

        System.out.print("Nova Marca (" + automovelExistente.getMarca() + "): ");
        String novaMarca = scanner.nextLine().trim();
        if (novaMarca.isEmpty()) {
            novaMarca = automovelExistente.getMarca();
        }

        int novoAno = automovelExistente.getAno();
        System.out.print("Novo Ano (" + automovelExistente.getAno() + "): ");
        String anoStr = scanner.nextLine().trim();
        if (!anoStr.isEmpty()) {
            try {
                novoAno = Integer.parseInt(anoStr);
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido. Mantendo o ano atual.");
            }
        }

        double novoValor = automovelExistente.getValor();
        System.out.print("Novo Valor (" + String.format("%.2f", automovelExistente.getValor()) + "): ");
        String valorStr = scanner.nextLine().trim();
        if (!valorStr.isEmpty()) {
            try {
                novoValor = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Mantendo o valor atual.");
            }
        }

        dao.alterarAutomovel(placa, novoModelo, novaMarca, novoAno, novoValor);
    }

    private static void consultarAutomovel() {
        System.out.println("\n--- Consultar Automóvel ---");
        System.out.print("Digite a placa do automóvel a ser consultado: ");
        String placa = scanner.nextLine().trim();
        if (placa.isEmpty()) {
            System.out.println("Placa não pode ser vazia.");
            return;
        }

        Automovel automovel = dao.buscarAutomovelPorPlaca(placa);
        if (automovel != null) {
            System.out.println("Automóvel encontrado:");
            System.out.println(automovel);
        } else {
            System.out.println("Automóvel com placa " + placa + " não encontrado.");
        }
    }

    private static void listarAutomoveis() {
        System.out.println("\n--- Listar Automóveis ---");
        System.out.print("Ordenar por (placa, modelo, marca)? ");
        String criterio = scanner.nextLine().trim();

        ArrayList<Automovel> lista = dao.listarAutomoveis(criterio);

        if (lista.isEmpty()) {
            System.out.println("Nenhum automóvel cadastrado.");
        } else {
            System.out.println("\n--- Lista de Automóveis (" + criterio + ") ---");
            for (Automovel a : lista) {
                System.out.println(a);
            }
            System.out.println("------------------------------------");
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                double numero = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, digite um número decimal (ex: 123.45 ou 123,45): ");
            }
        }
    }
}
