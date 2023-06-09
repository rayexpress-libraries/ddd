package io.github.rayexpresslibraries.ddd.domain.pagination.query;

public class Sort {
    private final String property;
    private final Direction direction;

    private Sort(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    public String property() {
        return this.property;
    }

    public Direction direction() {
        return this.direction;
    }

    private static Sort by(final String[] sort, Property aProperty) {
        final String property = aProperty.getDomainProperty(sort[0]);
        try {
            final String direction = sort[1];
            return new Sort(property, Direction.valueOf(direction.toUpperCase()));
        } catch (Exception e) {
            return new Sort(property, defaultDirection());
        }
    }

    /**
     * @param sort '[0] - property' e '[1] - direction'
     * - Direction - {@link Direction}
     * - Property - {@link Property}
     */
    public static Sort by(final String[] sort, Class<? extends Property> sortProperty) {
        try {
            return Sort.by(sort, sortProperty.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Sort.unsorted();
        }
    }

    public static Sort by(final String property, final Direction direction, final Class<? extends Property> sortProperty) {
        try {
            return Sort.by(new String[]{property, direction.name()}, sortProperty.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Sort.unsorted();
        }
    }

    /**
     * @param direction    see algo {@link Direction}
     * @param sortProperty see algo {@link Property}
     */
    public static Sort by(final String property, final String direction, final Class<? extends Property> sortProperty) {
        try {
            return Sort.by(new String[]{property, direction}, sortProperty.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Sort.unsorted();
        }
    }

    public static Direction defaultDirection() {
        return Direction.ASC;
    }

    public static Sort unsorted() {
        return new Sort("", defaultDirection());
    }

    @Override
    public String toString() {
        return "Sort{" +
                "property='" + property + '\'' +
                ", direction=" + direction +
                '}';
    }
}
