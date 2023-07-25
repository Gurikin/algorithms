package org.gurikin.queue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomHeap {
    private static final List<Integer> heap = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/heap_input.txt"));
        int opNum = Integer.parseInt(input.remove(0));
        for (int i = 0; i < opNum; i++) {
            String[] command = input.get(i).split(" ");
            if ("0".equals(command[0])) {
                pushHeap(heap, Integer.parseInt(command[1]));
            } else {
                System.out.println(popHeap(heap));
            }
        }
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
}
