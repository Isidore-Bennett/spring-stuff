package com.coughy.maybe.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public class PageUtility {

    public static Pageable setPageRequest(Map<String, String> headers) {
        int page = Integer.parseInt(headers.get("pageable-page".toLowerCase()));
        int size = Integer.parseInt(headers.get("pageable-size".toLowerCase()));
        String sort = headers.get("pageable-sortBy".toLowerCase());
        String dir = headers.get("pageable-dir".toLowerCase());
        if (sort == null && dir == null) {
            return PageRequest.of(page, size);
        } else if (sort != null) {
            return PageRequest.of(page, size, Sort.by(List.of(new Sort.Order(Sort.Direction.ASC, sort))));
        } else if (dir != null) {
            return PageRequest.of(page, size, dir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC);
        } else {
            return PageRequest.of(page, size, Sort.by(List.of(new Sort.Order(dir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sort))));
        }
    }
}
