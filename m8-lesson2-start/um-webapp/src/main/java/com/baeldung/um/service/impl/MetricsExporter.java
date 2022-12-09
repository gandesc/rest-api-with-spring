package com.baeldung.um.service.impl;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
final class MetricsExporter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedDelay = 100 * 30)
    public void exportData() {
        meterRegistry.getMeters()
                .stream()
                .forEach(this::log);
    }

    private void log(final Meter m) {
        StringBuilder result = new StringBuilder();

        for(Measurement measurement: m.measure()) {
            result.append("[");
            result.append(measurement.getStatistic().name());
            result.append(" ");
            result.append(measurement.getValue());
            result.append("]");
        }

        logger.info("Reporting metric {} = {}", m.getId(), result);
    }
}
