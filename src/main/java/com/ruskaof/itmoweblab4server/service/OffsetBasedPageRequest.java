package com.ruskaof.itmoweblab4server.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Objects;

public class OffsetBasedPageRequest implements Pageable, Serializable {

    private static final long serialVersionUID = -25822477129613575L;

    private final int limit;
    private final long offset;
    private final Sort sort;

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     * @param sort   can be {@literal null}.
     */
    public OffsetBasedPageRequest(long offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero! It was " + offset);
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset     zero-based offset.
     * @param limit      the size of the elements to be returned.
     * @param direction  the direction of the {@link Sort} to be specified, can be {@literal null}.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public OffsetBasedPageRequest(long offset, int limit, Sort.Direction direction, String... properties) {
        this(offset, limit, Sort.by(direction, properties));
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     */
    public OffsetBasedPageRequest(int offset, int limit) {
        this(offset, limit, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return Math.toIntExact(offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetBasedPageRequest previous() {
        return hasPrevious() ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetBasedPageRequest((long) pageNumber * getPageSize(), getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffsetBasedPageRequest that = (OffsetBasedPageRequest) o;
        return limit == that.limit && offset == that.offset && Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, offset, sort);
    }

    @Override
    public String toString() {
        return "OffsetBasedPageRequest{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", sort=" + sort +
                '}';
    }
}