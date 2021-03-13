package io.github.util;


import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.entity.Configuration;


public class MyDefaultContextFactory extends DefaultContextFactory {

    public MyDefaultContextFactory() {
        this(new Configuration("http://127.0.0.1:8000/"));

    }

    public MyDefaultContextFactory(Configuration configuration) {
        super(configuration);
    }

//    @Override
//    public ObjectProvider<String> getUrlPrefixProvider() {
//        return super.getUrlPrefixProvider();
//    }
//
//    @Override
//    public AbstractRequester getAbstractRequester() {
//        return new DefaultRequester(new DefaultRequestHandlerFactory(), this.getRestTemplateProvider());
//    }

}

