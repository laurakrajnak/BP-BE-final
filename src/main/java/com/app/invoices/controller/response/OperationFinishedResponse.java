package com.app.invoices.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationFinishedResponse {
    private long id;

    public OperationFinishedResponse(Long id) {
        this.id = id;
    }
}

