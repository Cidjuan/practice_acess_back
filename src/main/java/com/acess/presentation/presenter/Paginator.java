package com.acess.presentation.presenter;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Paginator {

    private Integer page;
    private Long size;
    private long totalElements;
    private long totalPages;
    @Builder.Default
    private List data = new ArrayList();

}