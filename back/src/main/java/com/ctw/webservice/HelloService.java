package com.ctw.webservice;

import javax.jws.WebService;

/**
 * ctw com.ctw.webservice
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/02 13: 17
 * @Version 1.0
 * @explain：............
 */
@WebService
public class HelloService {
    public String helloWorld(){
        return "AAA";
    }
}
