package csg.monthly.expensies;

import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.view.MonthlyExpensesView;

@SpringBootApplication
public class Application extends MonthlyExpensesView {

    private static ApplicationContext APPLICATION_CONTEXT = null;

    @Autowired
    private ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static void main(final String... args) {
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
        //        final ItemService itemService = getApplicationContext().getBean(ItemService.class);
        //        itemService.save(getDummyItem());
        //        itemService.getItems().forEach(System.out::println);
    }

    private static Tag getDummyTag() {
        final int prio = 1;
        return new Tag("Dummy " + prio, prio);
    }

    private static Item getDummyItem() {
        final int item = 1;
        return new Item("Dummy " + item, getDummyTag(), 9, Date.valueOf("2020-3-05"));
    }

    @PostConstruct
    public void postConstruct() {
        APPLICATION_CONTEXT = applicationContext;
    }

}
