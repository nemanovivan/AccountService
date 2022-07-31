package org.accountservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    private Long amount;
}
