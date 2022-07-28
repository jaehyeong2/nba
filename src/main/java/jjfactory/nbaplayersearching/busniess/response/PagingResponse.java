package jjfactory.nbaplayersearching.busniess.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagingResponse {
    private int currentPage;
    private int totalPage;
    private int totalCount;
}
