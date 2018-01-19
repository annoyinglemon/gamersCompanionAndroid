package lemond.annoying.gamerscompanion;


import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulerRule implements TestRule {

    private Scheduler trampoline = Schedulers.trampoline();

    private Function<Scheduler, Scheduler> schedulerFunction = scheduler -> trampoline;

    private Function<Callable<Scheduler>, Scheduler> schedulerFunctionLazy = schedulerCallable -> trampoline;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy);

                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction);
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction);
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction);

                base.evaluate();

                RxAndroidPlugins.reset();
                RxJavaPlugins.reset();
            }
        };
    }
}