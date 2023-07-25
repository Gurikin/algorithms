package org.gurikin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new LinkedList<>();
        timeToFillArrayList(50000);
        timeToFillLinkedList(50000);


        // System.out.println(Set.of("NEXT", "OLD", "CURRENT"));
        // Parent parent = new Parent(new Child(null, "1"));
        // System.out.println(of(parent, parent.getChild(), parent.getChild().getValue()).anyMatch(Objects::isNull));
        //
        // Parent parent2 = new Parent(null);
        // System.out.println(of(parent, parent.getChild(), parent.getChild().getValue()).anyMatch(Objects::isNull));
        //
        // Parent parent3 = null;
        // System.out.println(of(parent, parent.getChild(), parent.getChild().getValue()).anyMatch(Objects::isNull));
    }

    private static void timeToFillLinkedList(int num) throws InterruptedException {
        long timeToFillSum = 0;
        for (int j = 0; j < 5; j++) {
            LinkedList<Integer> ll = new LinkedList<>();
            long startAt = System.nanoTime();
            for (int i = 0; i < num; i++) {
                ll.add(i);
            }
            timeToFillSum += (System.nanoTime() - startAt);
            ll.clear();
            Thread.sleep(100);
        }
        System.out.println("Time (in nanos) to fill LList = " + timeToFillSum);
    }

    private static void timeToFillArrayList(int num) throws InterruptedException {
        long timeToFillSum = 0;
        for (int j = 0; j < 5; j++) {
            ArrayList<Integer> al = new ArrayList<>();
            long startAt = System.nanoTime();
            for (int i = 0; i < num; i++) {
                al.add(i);
            }
            timeToFillSum += (System.nanoTime() - startAt);
            al.clear();
            Thread.sleep(100);
        }
        System.out.println("Time (in nanos) to fill AList = " + timeToFillSum);
    }
}