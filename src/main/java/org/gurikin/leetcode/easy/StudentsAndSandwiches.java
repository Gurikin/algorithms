package org.gurikin.leetcode.easy;

import java.util.LinkedList;
import java.util.Random;

public class StudentsAndSandwiches {
    public static int countStudents(int[] students, int[] sandwiches) {
        LinkedList<Integer> studentQ = new LinkedList<Integer>();
        LinkedList<Integer> sandwichQ = new LinkedList<Integer>();
        var circleCnt = 0;
        var squadCnt = 0;
        for (int i = 0; i < students.length; i++) {
            studentQ.add(students[i]);
            sandwichQ.add(sandwiches[i]);
            if (students[i] == 0) {
                circleCnt++;
            } else {
                squadCnt++;
            }
        }
        while (!studentQ.isEmpty()) {
            var student = studentQ.pollFirst();
            circleCnt -= (student == 0) ? 1 : 0;
            squadCnt -= (student == 1) ? 1 : 0;
//            System.out.println("Student: " + student);
            var sandwich = sandwichQ.peekFirst();
//            System.out.println("Sandwich: " + sandwich);
            if (student.equals(sandwich)) {
                sandwichQ.pollFirst();
            } else {
                studentQ.addLast(student);
                circleCnt += (student == 0) ? 1 : 0;
                squadCnt += (student == 1) ? 1 : 0;
                if ((studentQ.size() == circleCnt && sandwich == 1) || (studentQ.size() == squadCnt && sandwich == 0)) {
                    break;
                }
            }
        }
        return studentQ.size();
    }

    public static void main(String[] args) {
        var capacity = 10000;
        var students = new int[capacity];
        var sandwiches = new int[capacity];
        var random = new Random();
        for (int i = 0; i < capacity; i++) {
            students[i] = (random.nextBoolean()) ? 0 : 1;
            sandwiches[i] = (random.nextBoolean()) ? 0 : 1;
        }
        var start = System.nanoTime();
        var hangrySt = countStudents(students, sandwiches);
        var time = (System.nanoTime() - start) / 1000000;
        System.out.format("Count hangry students in queue = %d. Hangry Students: %d. Time: [%d]ms", capacity, hangrySt, time);
    }
}
