package com.atrvasa.core.request;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.infra.StringTools;

public class RequestInspector {

    public void initialize(String request) throws RequestIsNullException {
        if (StringTools.isNullOrEmpty(request))
            throw AenahInitializer.init(
                    new RequestIsNullException(),
                    new AenahDetail()
                            .type(RequestInspector.class)
                            .function("initialize")
            );
    }
}
