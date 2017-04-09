package objects.collections;

import objects.collections.inner.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import static objects.collections.inner.UserGenerator.generate;

/**
 * Created by xmitya on 20.10.16.
 */
public class Tasks2 {
    public static void main(String[] args) {
        List<User> users = generate(10);
        System.out.println("User1:");
        for (User u : users) {
            System.out.println(u);
        }

        System.out.println("\nSorted by company and name:");
        NavigableSet<User> sorted = sortedByCompanyAndName(users);
        for (User u : sorted) {
            System.out.println(u);
        }

        System.out.println("\nSorted by salary and name:");
        sorted = sortedBySalaryAndName(users);
        for (User u : sorted) {
            System.out.println(u);
        }

        System.out.println("\nSorted by salary, age, company and name:");
        sorted = sortedBySalaryAgeCompanyAndName(users);
        for (User u : sorted) {
            System.out.println(u);
        }

//        iterator
        List<User> users2 = generate(10);
        System.out.println("\nUser2:");
        for (User u : users2) {
            System.out.println(u);
        }
        System.out.println("\nIterator:");
        Iterator<User> iter = viewIterator(users, users2);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {
        NavigableSet<User> sorted = new TreeSet<>(new User.CompanyAndNameCompare());
        sorted.addAll(users);
        return sorted;
    }


    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {
        NavigableSet<User> sorted = new TreeSet<>(new User.SalaryAndNameCompare());
        sorted.addAll(users);
        return sorted;
    }

    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {
        NavigableSet<User> sorted = new TreeSet<>(new User.SalaryAgeCompanyAndNameCompare());
        sorted.addAll(users);
        return sorted;
    }

    private static <T> Iterator<T> viewIterator(Iterable<T> it1, Iterable<T> it2) {
        return new MergeTwoIterators<T>(it1, it2);
    }

    private static class MergeTwoIterators<T> implements Iterator<T> {
        List<Iterator<T>> iterators;
        int current;

        MergeTwoIterators(Iterable<T> it1, Iterable<T> it2) {
            Iterator<T> iter1 = it1.iterator();
            Iterator<T> iter2 = it2.iterator();

            iterators = new ArrayList<>();
            iterators.add(iter1);
            iterators.add(iter2);

            current = 0;
        }

        @Override
        public boolean hasNext() {
            if (current < iterators.size() && !iterators.get(current).hasNext()) {
                current++;
            }
            return current < iterators.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            return iterators.get(current).next();
        }
    }
}
