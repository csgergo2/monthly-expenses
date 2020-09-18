package csg.monthly.expensies;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import csg.monthly.expensies.view.MonthlyExpensesView;

@SpringBootApplication
public class Application extends MonthlyExpensesView {

    private static ApplicationContext APPLICATION_CONTEXT = null;

    @Autowired
    private ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return APPLICATION_CONTEXT.getBean(clazz);
    }

    public static void main(final String... args) {
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }

    @PostConstruct
    public void postConstruct() {
        APPLICATION_CONTEXT = applicationContext;
    }

}
