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

    private static final int NUM_THREADS =
            4;


    public static void main(String[] args)
            throws Exception {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                " EXPERIMENTO COM CPU AFFINITY"
        );

        System.out.println(
                "=========================================="
        );

        int cpus =
                Runtime.getRuntime()
                        .availableProcessors();

        System.out.println(
                "CPUs lógicos disponíveis: "
                + cpus
        );

        System.out.println(
                "Threads utilizadas: "
                + NUM_THREADS
        );

        if (cpus < NUM_THREADS) {

            System.out.println(
                    "ATENÇÃO: existem menos CPUs "
                    + "lógicos que threads."
            );
        }


        // -----------------------------------------
        // VETOR
        // -----------------------------------------

        int[] vetor =
                new int[TAMANHO];

        for (int i = 0;
             i < vetor.length;
             i++) {

            vetor[i] = 1;
        }


        // -----------------------------------------
        // EXECUTOR
        // -----------------------------------------

        ThreadFactory factory =
                new AffinityThreadFactory();

        ExecutorService executor =
                Executors.newFixedThreadPool(
                        NUM_THREADS,
                        factory
                );


        // -----------------------------------------
        // TAREFAS
        // -----------------------------------------

        List<Future<Long>> futures =
                new ArrayList<>();

        int bloco =
                vetor.length / NUM_THREADS;


        long inicio =
                System.nanoTime();


        for (int i = 0;
             i < NUM_THREADS;
             i++) {

            int inicioBloco =
                    i * bloco;

            int fimBloco;

            if (i == NUM_THREADS - 1) {

                fimBloco =
                        vetor.length;

            } else {

                fimBloco =
                        inicioBloco + bloco;
            }


            final int inicioFinal =
                    inicioBloco;

            final int fimFinal =
                    fimBloco;


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
                                somar(
                                        vetor,
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


        // -----------------------------------------
        // RESULTADOS
        // -----------------------------------------

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
    }


    // -----------------------------------------
    // SOMA
    // -----------------------------------------

    private static long somar(
            int[] vetor,
            int inicio,
            int fim) {

        long soma = 0;

        for (int i = inicio;
             i < fim;
             i++) {

        // retardo para ver o uso de cpu
         //cl
         // cls
             for (int z = 0;   z < 10000; z++) {}
                  
             soma += vetor[i];
        }
       
        return soma;
    }


    // -----------------------------------------
    // THREAD FACTORY
    // -----------------------------------------

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
