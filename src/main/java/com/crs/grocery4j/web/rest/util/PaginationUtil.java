package com.crs.grocery4j.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">GitHub API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */

public final class PaginationUtil {

    public static final int DEFAULT_PAGE = 1;

    public static final int MIN_PAGE = 1;

    public static final int DEFAULT_OFFSET = 0;

    public static final int MIN_OFFSET = 0;

    public static final int DEFAULT_LIMIT = 20;

    public static final int MAX_LIMIT = 100;

    private PaginationUtil() {
    }

    public static HttpHeaders generatePaginationHttpHeaders(Page page, String baseUrl)
        throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl, Integer pageNumber, Integer limit)
        throws URISyntaxException {

        if (pageNumber == null || pageNumber < MIN_PAGE) {
            pageNumber = DEFAULT_PAGE;
        }
        if (limit == null || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        String link = "";
        if (pageNumber < page.getTotalPages()) {
            link = "<" + (new URI(baseUrl +"?page=" + (pageNumber + 1) + "&per_page=" + limit)).toString()
                + ">; rel=\"next\",";
        }
        if (pageNumber > 1) {
            link += "<" + (new URI(baseUrl +"?page=" + (pageNumber - 1) + "&per_page=" + limit)).toString()
                + ">; rel=\"prev\",";
        }
        link += "<" + (new URI(baseUrl +"?page=" + page.getTotalPages() + "&per_page=" + limit)).toString()
            + ">; rel=\"last\"," +
            "<" + (new URI(baseUrl +"?page=" + 1 + "&per_page=" + limit)).toString()
            + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String generateUri(String baseUrl, int page, int size) throws URISyntaxException {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
    }

    public static Pageable generatePageRequest(Integer page, Integer limit) {
        return generatePageRequest(page, limit, null);
    }

    public static Pageable generatePageRequestFromOffset(Integer offset, Integer limit) {
        return generatePageRequestFromOffset(offset, limit, null);
    }

    // generates a page request given the page number and limit of records to return in the page.
    // this is used where the request will specify the actual page to be used, rather than how
    // many records to skip.
    public static Pageable generatePageRequest(Integer page, Integer limit, Sort sort) {
        if (page == null || page < MIN_PAGE) {
            page = DEFAULT_PAGE;
        }
        if (limit == null || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }

        if(null == sort){
            return new PageRequest(page - 1, limit);
        } else {
            return new PageRequest(page - 1, limit, sort);
        }

    }

    // generates a page request given the number of records to skip and limit of records to return in a page.
    // this is used where the request specifies how many records are being skipped before the page that is being
    // currently accessed. Note that the offset here is expected to be 0 based (for the first record, you're skipping
    // over 0 records).
    public static Pageable generatePageRequestFromOffset(Integer offset, Integer limit, Sort sort) {
        if (offset == null || offset < MIN_OFFSET) {
            offset = DEFAULT_OFFSET;
        }
        if (limit == null || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }

        int page = (int) Math.floor((double) offset / limit) + 1;

        if(null == sort){
            return new PageRequest(page - 1, limit);
        } else {
            return new PageRequest(page - 1, limit, sort);
        }


    }

    public static Sort buildSort(List<String> sortStrings){
        Sort sort = null;

        if(null != sortStrings) {
            for (String sortString : sortStrings) {
                String[] sortPieces = sortString.split(":");

                if (null == sort) {
                    sort = new Sort(Sort.Direction.fromString(sortPieces[1]), sortPieces[0]);
                } else {
                    sort = sort.and(new Sort(Sort.Direction.fromString(sortPieces[1]), sortPieces[0]));
                }
            }
        }

        return sort;
    }


}
