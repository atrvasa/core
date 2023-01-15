package com.atrvasa.core.request;

import com.atrvasa.exception.AenahDetail;
import com.atrvasa.exception.AenahInitializer;
import com.atrvasa.infra.StringTools;
import com.atrvasa.infra.service.SessionModel;
import com.atrvasa.json.JsonConverter;
import com.atrvasa.json.exceptions.JsonObjectIsNullException;
import com.atrvasa.json.exceptions.JsonParserException;

public class RequestInspector {

    /**
     * check parameter is not null or empty.
     *
     * @param request is json string.
     * @return after load json string, return Request Model {@link com.atrvasa.core.request.RequestModel}
     * @throws RequestIsNullException if request is null or empty.
     */
    public RequestModel initialize(String request) throws RequestIsNullException, PluginIsNullException, JsonParserException, JsonObjectIsNullException {
        if (StringTools.isNullOrEmpty(request))
            throw AenahInitializer.init(
                    new RequestIsNullException(),
                    new AenahDetail()
                            .type(RequestInspector.class)
                            .function("initialize")
            );

        return loadRequest(request);
    }

    /**
     * Request must be contain plugin,
     * model and method or delegate,
     * session if from logged in user,
     * data if necessary as a json
     *
     * @param request is a Json String
     * @return Request Model {@link com.atrvasa.core.request.RequestModel}
     * @throws JsonParserException
     */
    private RequestModel loadRequest(String request) throws PluginIsNullException, JsonParserException, JsonObjectIsNullException {
        JsonConverter jsonConverter = new JsonConverter(request);

        RequestModel requestModel = new RequestModel();
        requestModel.setSession(jsonConverter.getFieldAsModel("session", SessionModel.class, null));
        requestModel.setPlugin(jsonConverter.getFieldAsString("plugin", null));
        if (requestModel.getPlugin() == null)
            throw AenahInitializer.init(
                    new PluginIsNullException(),
                    new AenahDetail()
                            .type(RequestInspector.class)
                            .function("loadRequest")
            );
        requestModel.setModel(jsonConverter.getFieldAsString("model", null));
        requestModel.setMethod(jsonConverter.getFieldAsString("method", null));
        requestModel.setDelegate(jsonConverter.getFieldAsString("delegate", null));
        requestModel.setData(jsonConverter.getFieldAsJsonObject("data", null));

        return requestModel;
    }
}
