package com.pheonix.core.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingRequest<T> {
	private Integer pageNumber;
	private Integer pageSize;
	private T request;
}
