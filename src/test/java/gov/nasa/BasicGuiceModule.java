package gov.nasa;

import com.google.inject.AbstractModule;
import gov.nasa.web.pages.HomePage;

public class BasicGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HomePage.class).toInstance(new HomePage());
    }
}
