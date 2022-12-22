package services;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class FileSizeSorter {
        public static Map<String, Long> sortFileSizes(Map<String, Long> fileSizes) {
            TreeMap<String, Long> sortedFileSizes = new TreeMap<>(new ValueComparator(fileSizes));
            sortedFileSizes.putAll(fileSizes);
            return sortedFileSizes;
        }

        static class ValueComparator implements Comparator<String> {
            Map<String, Long> fileSizes;

            public ValueComparator(Map<String, Long> fileSizes) {
                this.fileSizes = fileSizes;
            }

            @Override
            public int compare(String s1, String s2) {
                if (fileSizes.get(s1) >= fileSizes.get(s2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
