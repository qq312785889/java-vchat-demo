package com.demo.vchat.vchat.exception;

import com.demo.vchat.vchat.domain.ResultEnum;

public class CheckDataException extends RuntimeException {
    //注意，在spring中，只有RuntimeException才会进行事务回滚，Exception不会进行事务回滚
    private Integer code;

    public CheckDataException (ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
