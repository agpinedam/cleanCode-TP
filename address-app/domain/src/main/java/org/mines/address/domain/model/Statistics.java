package org.mines.address.domain.model;

import java.util.Map;

public record Statistics(int totalPersons, int totalBirthCertificates, double averageAge, Map<String, Integer> personsByAgeGroup) {

    public static final class StatisticsBuilder {
        private int totalPersons;
        private int totalBirthCertificates;
        private double averageAge;
        private Map<String, Integer> personsByAgeGroup;

        private StatisticsBuilder() {
        }

        public static StatisticsBuilder aStatistics() {
            return new StatisticsBuilder();
        }

        public StatisticsBuilder withTotalPersons(int totalPersons) {
            this.totalPersons = totalPersons;
            return this;
        }

        public StatisticsBuilder withTotalBirthCertificates(int totalBirthCertificates) {
            this.totalBirthCertificates = totalBirthCertificates;
            return this;
        }

        public StatisticsBuilder withAverageAge(double averageAge) {
            this.averageAge = averageAge;
            return this;
        }

        public StatisticsBuilder withPersonsByAgeGroup(Map<String, Integer> personsByAgeGroup) {
            this.personsByAgeGroup = personsByAgeGroup;
            return this;
        }

        public Statistics build() {
            return new Statistics(totalPersons, totalBirthCertificates, averageAge, personsByAgeGroup);
        }
    }
}
