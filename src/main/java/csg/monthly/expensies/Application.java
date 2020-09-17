package csg.monthly.expensies;

import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.practice.PrioGroupRepository;
import csg.monthly.expensies.practice.Test;

@SpringBootApplication
public class Application {

    private static ApplicationContext APPLICATION_CONTEXT = null;

    @Autowired
    private ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static void main(final String...args){
        SpringApplication.run(Application.class);
//        test();

        final ItemService itemService = getApplicationContext().getBean(ItemService.class);
        itemService.save(getDummyItem());
        itemService.getItems().forEach(System.out::println);
    }

    private static Tag getDummyTag() {
        final int prio = 1;
        return new Tag("Dummy " + prio, prio);
    }

    private static Item getDummyItem() {
        final int item = 1;
        return new Item("Dummy " + item, getDummyTag(), 9, Date.valueOf("2020-3-05"));
    }

    private static void test() {
        Test test = getApplicationContext().getBean(Test.class);
        test.print();
        PrioGroupRepository repo = getApplicationContext().getBean(PrioGroupRepository.class);
        repo.findAll().forEach(System.out::println);
    }

    @PostConstruct
    public void postContruct() {
        APPLICATION_CONTEXT = applicationContext;
    }

}
