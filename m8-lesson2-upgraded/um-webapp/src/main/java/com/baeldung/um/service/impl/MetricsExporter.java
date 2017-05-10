package com.baeldung.um.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.buffer.BufferMetricReader;
import org.springframework.scheduling.annotation.Scheduled;

//@Component
final class MetricsExporter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BufferMetricReader metricReader;

    @Autowired
    private CounterService counterService;

    @Scheduled(fixedRate = 1000 * 30) // every 30 seconds
    public void exportMetrics() {
        metricReader.findAll().forEach(this::log);
    }

    private void log(final Metric<?> m) {
        logger.info("Reporting metric {}={}", m.getName(), m.getValue());
        counterService.reset(m.getName());
    }
}