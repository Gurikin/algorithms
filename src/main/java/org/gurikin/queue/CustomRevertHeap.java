package org.gurikin.queue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomRevertHeap {
    private static final List<Integer> heap = new ArrayList<>();
    private static final LinkedList<Integer> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Long startAt = System.currentTimeMillis();
        List<String> input = Files.readAllLines(Path.of("src/main/resources/sorted_heap_input.txt"));
        int opNum = Integer.parseInt(input.get(0));
        String[] numbers = input.get(1).split(" ");
        Long endAt = System.currentTimeMillis();
        System.out.println("Time := " + (endAt - startAt));
        startAt = System.currentTimeMillis();
        for (int i = 0; i < opNum; i++) {
            pushHeap(heap, Integer.parseInt(numbers[i]));
            // heap.add(Integer.parseInt(numbers[i]));
        }
        endAt = System.currentTimeMillis();
        System.out.println("Time := " + (endAt - startAt));
        startAt = System.currentTimeMillis();
        for (int i = heap.size() - 1; i >= 0; i--) {
            sortElement(heap, i);
        }
        endAt = System.currentTimeMillis();
        System.out.println("Time := " + (endAt - startAt));
        startAt = System.currentTimeMillis();
        result.forEach(i -> System.out.print(i + " "));
        endAt = System.currentTimeMillis();
        System.out.println("Time := " + (endAt - startAt));
    }

    private static void pushHeap(List<Integer> heap, Integer i) {
        heap.add(i);
        int pos = heap.size() - 1;
        while (pos > 0 && heap.get(pos) > heap.get((pos - 1) / 2)) {
            Integer curr = heap.get(pos);
            Integer prev = heap.get((pos - 1) / 2);

            heap.set((pos - 1) / 2, curr);
            heap.set(pos, prev);

            pos = (pos - 1) / 2;
        }
    }

    private static Integer popHeap(List<Integer> heap) {
        int result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        int pos = 0;
        while (((pos * 2 + 2) < (heap.size()))) {
            int minSonIndex = pos * 2 + 1;
            if (heap.get(pos * 2 + 2) > heap.get(minSonIndex)) {
                minSonIndex = pos * 2 + 2;
            }
            if (heap.get(pos) < heap.get(minSonIndex)) {
                int temp = heap.get(minSonIndex);
                heap.set(minSonIndex, heap.get(pos));
                heap.set(pos, temp);
                pos = minSonIndex;
            } else {
                break;
            }
        }
        heap.remove(heap.size() - 1);
        return result;
    }

    private static void sortElement(List<Integer> heap, Integer posNonSortedElement) {
        if ((posNonSortedElement - 1) < 0) {
            result.push(heap.get(posNonSortedElement));
            return;
        }
        int nonSortedElement = heap.get(posNonSortedElement);
        heap.set(posNonSortedElement, heap.get(0));
        heap.set(0, nonSortedElement);
        shiftDown(heap, posNonSortedElement - 1);
    }

    private static void shiftDown(List<Integer> heap, int elementsNum) {
        List<Integer> tempHeap = new ArrayList<>(heap.subList(0, elementsNum + 1));
        result.push(heap.get(heap.size() - 1));
        heap.clear();
        for (int i = 0; i <= elementsNum; i++) {
            pushHeap(heap, tempHeap.get(i));
        }
    }
}
