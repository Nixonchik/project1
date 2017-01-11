package com.korobkin.dao.car.filters;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class EngineFilter implements CarFilter {
    float from, to;

    public EngineFilter(double from, double to) {
        if (from > to) {
            to = to + from;
            from = to - from;
            to = to - from;
        }

        this.from = (float) from;
        this.to = (float) to;
    }

    @Override
    public String stringPattern() {
        StringBuilder s = new StringBuilder();

        s.append("car.engine BETWEEN ")
                .append(from)
                .append(" AND ")
                .append(to);

        return s.toString();
    }
}
