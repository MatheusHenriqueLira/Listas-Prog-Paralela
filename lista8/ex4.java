package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import net.openhft.affinity.Affinity;

public class ExperimentoAffinity {

    private static final int TAMANHO =
            1_000_000_000;

    private static final long ITERACOES_PESADAS =
            200_000_000L;

    private static final int NUM_THREADS =
            4;

    @FunctionalInterface
    private interface Tarefa {
        long executar(long inicio, long fim);
    }

    public static void main(String[] args)
            throws Exception {

        int cpus =
                Runtime.getRuntime()
                        .availableProcessors();
        System.out.println(
                "CPUs lógicos disponíveis: "
                + cpus);
        System.out.println(
                "Threads utilizadas: "
                + NUM_THREADS);

        if (cpus < NUM_THREADS) {
            System.out.println(
                    "ATENÇÃO: existem menos CPUs "
                    + "lógicos que threads.");}

        int[] vetor =
                new int[TAMANHO];
        for (int i = 0;
             i < vetor.length;
             i++) {

            vetor[i] = 1;}

        double tempo1 = executar(
                "TAREFA 1 - Soma do vetor",
                TAMANHO,
                (ini, fim) -> somar(vetor, (int) ini, (int) fim)
        );
        double tempo2 = executar(
                "TAREFA 2 - trabalhoPesado (sin/cos/sqrt/log)",
                ITERACOES_PESADAS,
                ExperimentoAffinity::trabalhoPesado
        );
        double tempo3 = executar(
                "TAREFA 3 - trabalhoPesado2 (atan/exp/tanh/pow)",
                ITERACOES_PESADAS,
                ExperimentoAffinity::trabalhoPesado2
        );

        System.out.println();

        System.out.printf(
                "%-10s %12s %16s %14s %14s%n",
                "Tarefa", "Tempo (s)", "Elementos",
                "ns/elemento", "Rel. Tarefa 1"
        );
        imprimirLinha("Tarefa 1", tempo1, TAMANHO, tempo1);
        imprimirLinha("Tarefa 2", tempo2, ITERACOES_PESADAS, tempo1);
        imprimirLinha("Tarefa 3", tempo3, ITERACOES_PESADAS, tempo1);
        System.out.println();
        System.out.printf(
                "Tarefa 3 / Tarefa 2 (mesmo nº de iterações) = %.2fx%n",
                tempo3 / tempo2
        );}

    private static void imprimirLinha(
            String nome,
            double tempo,
            long elementos,
            double tempoBase) {
        System.out.printf(
                "%-10s %12.3f %,16d %14.2f %13.2fx%n",
                nome,
                tempo,
                elementos,
                tempo * 1_000_000_000.0 / elementos,
                tempo / tempoBase
        );}

    private static double executar(
            String nomeTarefa,
            long total,
            Tarefa tarefa)
            throws Exception {

        System.out.println();
        System.out.println(
                "------------------------------------------"
        );
        System.out.println(nomeTarefa);

        ThreadFactory factory =
                new AffinityThreadFactory();

        ExecutorService executor =
                Executors.newFixedThreadPool(
                        NUM_THREADS,
                        factory
                );

        List<Future<Long>> futures =
                new ArrayList<>();

        long bloco =
                total / NUM_THREADS;

        long inicio =
                System.nanoTime();

        for (int i = 0;
             i < NUM_THREADS;
             i++) {

            final long inicioFinal =
                    i * bloco;

            final long fimFinal =
                    (i == NUM_THREADS - 1)
                            ? total
                            : inicioFinal + bloco;

            Future<Long> future =
                    executor.submit(() -> {

                        String thread =
                                Thread.currentThread()
                                        .getName();

                        System.out.println(
                                thread
                                + " iniciou processamento"
                                + " ["
                                + inicioFinal
                                + " - "
                                + fimFinal
                                + "]"
                        );

                        long resultado =
                                tarefa.executar(
                                        inicioFinal,
                                        fimFinal
                                );

                        System.out.println(
                                thread
                                + " terminou"
                        );

                        return resultado;
                    });

            futures.add(future);
        }

        long somaTotal = 0;

        for (Future<Long> future :
                futures) {

            somaTotal +=
                    future.get();
        }

        executor.shutdown();

        long fim =
                System.nanoTime();

        double tempo =
                (fim - inicio)
                        / 1_000_000_000.0;

        System.out.println();

        System.out.println(
                "Resultado = "
                + somaTotal
        );

        System.out.printf(
                "Tempo = %.3f segundos%n",
                tempo
        );

        return tempo;
    }

    private static long somar(
            int[] vetor,
            int inicio,
            int fim) {

        long soma = 0;

        for (int i = inicio;
             i < fim;
             i++) {
            for (int z = 0; z < 10000; z++) {}

            soma += vetor[i];
        }

        return soma;
    }

    private static long trabalhoPesado(
            long inicio,
            long fim) {

        long resultado = 0;

        for (long i = inicio; i < fim; i++) {

            double x = i;

            x = Math.sin(x);
            x = Math.cos(x);
            x = Math.sqrt(Math.abs(x));
            x = Math.log(x + 1);

            resultado += (long) (x * 1000);
        }

        return resultado;
    }

    private static long trabalhoPesado2(
            long inicio,
            long fim) {

        long resultado = 0;

        for (long i = inicio; i < fim; i++) {

            double x = i % 1000 + 1;

            x = Math.atan(x);
            x = Math.exp(-x);
            x = Math.tanh(x * 3.0);
            x = Math.pow(x + 1.0, 1.5);

            resultado += (long) (x * 1000);
        }

        return resultado;
    }

    static class AffinityThreadFactory
            implements ThreadFactory {

        private final AtomicInteger contador =
                new AtomicInteger(0);

        @Override
        public Thread newThread(
                Runnable runnable) {

            int cpu =
                    contador.getAndIncrement();

            Thread thread =
                    new Thread(() -> {

                        Affinity.setAffinity(
                                cpu
                        );

                        System.out.println(
                                Thread.currentThread()
                                        .getName()
                                + " associado ao CPU "
                                + cpu
                        );

                        runnable.run();
                    });

            thread.setName(
                    "Worker-" + cpu
            );

            return thread;
        }
    }
}
