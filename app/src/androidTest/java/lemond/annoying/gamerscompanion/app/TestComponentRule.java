package lemond.annoying.gamerscompanion.app;


import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import lemond.annoying.gamerscompanion.app.module.AppContextModule;

public class TestComponentRule implements TestRule {

    private final TestGamersAppComponent testGamersAppComponent;
    private final Context context;

    public TestComponentRule(Context context) {
        this.context = context;
        GamersApplication gamersApplication = (GamersApplication) context.getApplicationContext();
        this.testGamersAppComponent = DaggerTestGamersAppComponent.builder()
                .build();

    }


    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                GamersApplication application = (GamersApplication) context.getApplicationContext();
                // Set the TestComponent before the test runs
                application.setComponent(testGamersAppComponent);
                base.evaluate();
                // Clears the component once the tets finishes so it would use the default one.
                application.setComponent(null);
            }
        };
    }
}
