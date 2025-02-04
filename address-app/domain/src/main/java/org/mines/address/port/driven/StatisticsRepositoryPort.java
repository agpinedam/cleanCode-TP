package org.mines.address.port.driven;

import org.mines.address.domain.model.Statistics;

public interface StatisticsRepositoryPort {

    /**
     * Retrieves the general statistics of the registry.
     *
     * @return Statistics object containing total persons, total birth certificates, average age, etc.
     */
    Statistics getStatistics();
}
