package com.example.kanban.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KanbanGUI extends JFrame {

    private final JTextField campoTitulo;
    private final JTextArea campoDescricao;
    private final JComboBox<String> campoPrioridade;
    private final JTextField campoDataLimite;
    private final JPanel colunaAFazer;
    private final JPanel colunaEmProgresso;
    private final JPanel colunaConcluido;

    private final List<TarefaPanel> tarefasAFazer;
    private final List<TarefaPanel> tarefasEmProgresso;
    private final List<TarefaPanel> tarefasConcluidas;

    public KanbanGUI() {
        setTitle("Quadro Kanban");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tarefasAFazer = new ArrayList<>();
        tarefasEmProgresso = new ArrayList<>();
        tarefasConcluidas = new ArrayList<>();


        JPanel painelCriacao = new JPanel();
        painelCriacao.setLayout(new GridBagLayout());
        painelCriacao.setBorder(BorderFactory.createTitledBorder("Nova Tarefa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCriacao.add(new JLabel("Título:"), gbc);
        campoTitulo = new JTextField();
        gbc.gridx = 1;
        painelCriacao.add(campoTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCriacao.add(new JLabel("Descrição:"), gbc);
        campoDescricao = new JTextArea(3, 20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        painelCriacao.add(new JScrollPane(campoDescricao), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCriacao.add(new JLabel("Prioridade:"), gbc);
        campoPrioridade = new JComboBox<>(new String[]{"Baixa", "Média", "Alta"});
        gbc.gridx = 1;
        painelCriacao.add(campoPrioridade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCriacao.add(new JLabel("Data Limite (YYYY-MM-DD):"), gbc);
        campoDataLimite = new JTextField();
        gbc.gridx = 1;
        painelCriacao.add(campoDataLimite, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton botaoAdicionar = new JButton("Adicionar Tarefa");
        botaoAdicionar.addActionListener(e -> adicionarTarefa());
        painelCriacao.add(botaoAdicionar, gbc);

        add(painelCriacao, BorderLayout.NORTH);

        Color lilas = new Color(200, 162, 200);
        colunaAFazer = criarColuna("A Fazer", lilas);
        colunaEmProgresso = criarColuna("Em Progresso", lilas);
        colunaConcluido = criarColuna("Concluído", lilas);

        JPanel painelColunas = new JPanel(new GridLayout(1, 3, 10, 10));
        painelColunas.add(new JScrollPane(colunaAFazer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        painelColunas.add(new JScrollPane(colunaEmProgresso, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        painelColunas.add(new JScrollPane(colunaConcluido, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        add(painelColunas, BorderLayout.CENTER);
    }

    private JPanel criarColuna(String titulo, Color corDeFundo) {
        JPanel coluna = new JPanel();
        coluna.setBackground(corDeFundo);
        coluna.setLayout(new BoxLayout(coluna, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel(titulo, SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        coluna.add(labelTitulo);

        return coluna;
    }

    private void adicionarTarefa() {
        String titulo = campoTitulo.getText();
        String descricao = campoDescricao.getText();
        String prioridade = (String) campoPrioridade.getSelectedItem();
        String dataLimite = campoDataLimite.getText();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O título é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime dataCriacao = LocalDateTime.now();

        TarefaPanel tarefaPanel = new TarefaPanel(titulo, descricao, dataCriacao, prioridade, dataLimite);
        tarefasAFazer.add(tarefaPanel);
        colunaAFazer.add(tarefaPanel);
        colunaAFazer.revalidate();
        colunaAFazer.repaint();

        // Limpar campos de entrada
        campoTitulo.setText("");
        campoDescricao.setText("");
        campoPrioridade.setSelectedIndex(0);
        campoDataLimite.setText("");
    }

    private class TarefaPanel extends JPanel {
        private final String titulo;
        private final String descricao;
        private final LocalDateTime dataCriacao;
        private String prioridade;
        private final String dataLimite;

        private JButton botaoMover;

        public TarefaPanel(String titulo, String descricao, LocalDateTime dataCriacao, String prioridade, String dataLimite) {
            this.titulo = titulo;
            this.descricao = descricao;
            this.dataCriacao = dataCriacao;
            this.prioridade = prioridade;
            this.dataLimite = dataLimite;

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.BLACK)));

            JLabel labelTitulo = new JLabel("Título: " + titulo);
            JLabel labelDescricao = new JLabel("Descrição: " + descricao);
            JLabel labelDataCriacao = new JLabel("Data de Criação: " + dataCriacao.toString());
            JLabel labelPrioridade = new JLabel("Prioridade: " + prioridade);
            JLabel labelDataLimite = new JLabel("Data Limite: " + dataLimite);

            add(labelTitulo);
            add(labelDescricao);
            add(labelDataCriacao);
            add(labelPrioridade);
            add(labelDataLimite);

            botaoMover = new JButton("Mover para a próxima coluna");
            botaoMover.addActionListener(e -> moverTarefa());
            add(botaoMover);
        }

        private void moverTarefa() {
            JPanel colunaAtual = (JPanel) this.getParent();
            if (colunaAtual == colunaAFazer) {
                tarefasAFazer.remove(this);
                tarefasEmProgresso.add(this);
                colunaEmProgresso.add(this);
            } else if (colunaAtual == colunaEmProgresso) {
                tarefasEmProgresso.remove(this);
                tarefasConcluidas.add(this);
                colunaConcluido.add(this);
            }
            colunaAtual.revalidate();
            colunaAtual.repaint();
            ((JPanel) this.getParent()).revalidate();
            ((JPanel) this.getParent()).repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KanbanGUI kanbanGUI = new KanbanGUI();
            kanbanGUI.setVisible(true);
        });
    }
}
