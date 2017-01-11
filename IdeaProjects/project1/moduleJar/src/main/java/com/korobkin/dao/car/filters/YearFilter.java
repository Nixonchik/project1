package com.korobkin.dao.car.filters;


/**
 * Created by Java Developer on 24.11.2015.
 */
public class YearFilter implements CarFilter {
    int from, to;

    public YearFilter(int from, int to) {
        if (from > to) {
            to = to + from;
            from = to - from;
            to = to - from;
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String stringPattern() {
        StringBuilder s = new StringBuilder();
        s.append("car.year BETWEEN ")
                .append(from)
                .append(" AND ")
                .append(to);

        return s.toString();
    }
}
