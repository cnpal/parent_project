package com.pal.servicebase.exceptionhadler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author pal
 * @Date Created in 2020/9/4 13:16
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;
}
