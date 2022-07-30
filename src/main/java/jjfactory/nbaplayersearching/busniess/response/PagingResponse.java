package jjfactory.nbaplayersearching.busniess.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagingResponse<T> {
    private int currentPage;
    private int totalPage;
    private long totalCount;
    private List<T> dataList;

    public PagingResponse(Page<T> page) {
        this.currentPage = page.getPageable().getPageNumber() +1;
        this.totalPage = page.getTotalPages();
        this.totalCount = page.getTotalElements();
        this.dataList = page.getContent();
    }
}
