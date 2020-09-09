package io.github.util;


import io.github.q843705423.autumn.AbstractRequester;
import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.DefaultRequester;
import io.github.q843705423.autumn.ObjectProvider;
import io.github.q843705423.autumn.entity.Configuration;
import io.github.q843705423.autumn.request.facotory.DefaultHandlerFactory;
import org.springframework.web.client.RestTemplate;

public class MyDefaultContextFactory extends DefaultContextFactory {

    public MyDefaultContextFactory() {
        this(new Configuration("http://127.0.0.1:8080"));

    }

    public MyDefaultContextFactory(Configuration configuration) {
        super(configuration);
    }

    @Override
    public ObjectProvider<String> getUrlPrefixProvider() {
        return super.getUrlPrefixProvider();
    }

    @Override
    public AbstractRequester getAbstractRequester() {
        DefaultHandlerFactory abstractHandlerFactory = new DefaultHandlerFactory();
        return new DefaultRequester(abstractHandlerFactory, s -> new RestTemplate());
    }

}

