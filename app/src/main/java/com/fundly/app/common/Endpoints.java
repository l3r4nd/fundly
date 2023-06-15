package com.fundly.app.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public final String API = "/api";
    public final String PREFIX = "/v1";

    public final String API_PREFIX = API + PREFIX;

    public final String PAYMENT = API_PREFIX + "/payment";

    
}
