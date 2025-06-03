package com.projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class AutomovelDAO {
    private ArrayList<Automovel> automoveis;
    private final String ARQUIVO_DADOS = "dados/automoveis.txt";

    public AutomovelDAO() {
        this.automoveis = new ArrayList<>();
        carregarDados();
    }

    public boolean incluirAutomovel(Automovel novoAutomovel) {
        if (buscarAutomovelPorPlaca(novoAutomovel.getPlaca()) != null) {
            System.out.println("Erro: Já existe um automóvel com a placa " + novoAutomovel.getPlaca());
            return false;
        }
        this.automoveis.add(novoAutomovel);
        System.out.println("Automóvel incluído com sucesso!");
        return true;
    }

    public boolean removerAutomovel(String placa) {
        Iterator<Automovel> iterator = automoveis.iterator();
        while (iterator.hasNext()) {
            Automovel a = iterator.next();
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                iterator.remove();
                System.out.println("Automóvel com placa " + placa + " removido com sucesso!");
                return true;
            }
        }
        System.out.println("Erro: Automóvel com placa " + placa + " não encontrado para remoção.");
        return false;
    }

    public boolean alterarAutomovel(String placa, String novoModelo, String novaMarca, int novoAno, double novoValor) {
        Automovel automovelParaAlterar = buscarAutomovelPorPlaca(placa);
        if (automovelParaAlterar != null) {
            automovelParaAlterar.setModelo(novoModelo);
            automovelParaAlterar.setMarca(novaMarca);
            automovelParaAlterar.setAno(novoAno);
            automovelParaAlterar.setValor(novoValor);
            System.out.println("Dados do automóvel com placa " + placa + " alterados com sucesso!");
            return true;
        }
        System.out.println("Erro: Automóvel com placa " + placa + " não encontrado para alteração.");
        return false;
    }

    public Automovel buscarAutomovelPorPlaca(String placa) {
        for (Automovel a : automoveis) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Automovel> listarAutomoveis(String criterioOrdenacao) {
        ArrayList<Automovel> listaOrdenada = new ArrayList<>(this.automoveis);

        switch (criterioOrdenacao.toLowerCase()) {
            case "placa":
                listaOrdenada.sort(Comparator.comparing(Automovel::getPlaca));
                break;
            case "modelo":
                listaOrdenada.sort(Comparator.comparing(Automovel::getModelo));
                break;
            case "marca":
                listaOrdenada.sort(Comparator.comparing(Automovel::getMarca));
                break;
            default:
                System.out.println("Critério de ordenação inválido. Listando sem ordenação específica.");
                break;
        }
        return listaOrdenada;
    }

    public void salvarDados() {
        File diretorioDados = new File("dados");
        if (!diretorioDados.exists()) {
            diretorioDados.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Automovel a : automoveis) {
                writer.write(a.toCSV());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso em " + ARQUIVO_DADOS);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de dados não encontrado. Iniciando com lista vazia.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    try {
                        String placa = dados[0];
                        String modelo = dados[1];
                        String marca = dados[2];
                        int ano = Integer.parseInt(dados[3]);
                        double valor = Double.parseDouble(dados[4]);
                        automoveis.add(new Automovel(placa, modelo, marca, ano, valor));
                    } catch (NumberFormatException e) {
                        System.err.println("Erro de formato numérico ao carregar linha: " + linha);
                    }
                } else {
                    System.err.println("Linha com formato inválido no arquivo: " + linha);
                }
            }
            System.out.println("Dados carregados com sucesso de " + ARQUIVO_DADOS);
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}
