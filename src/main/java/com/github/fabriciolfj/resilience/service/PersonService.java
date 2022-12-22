package com.github.fabriciolfj.resilience.service;

import com.github.fabriciolfj.resilience.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonService {

    public List<Person> execute() {
        var persons =new ArrayList<Person>();

        int index = 0;

        while (index < 10) {
            var person = Person.builder()
                    .name("teste"+index)
                    .code(UUID.randomUUID().toString())
                    .build();

            persons.add(person);

            if (index == 5) {
                throw new RuntimeException();
            }

            index++;
        }

        return persons;
     }

    public List<Person> executeTime() {
        var persons =new ArrayList<Person>();

        int index = 0;

        while (index < 10) {
            var person = Person.builder()
                    .name("teste"+index)
                    .code(UUID.randomUUID().toString())
                    .build();

            persons.add(person);

           /* try {
                Thread.sleep(50000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            index++;*/
        }

        return persons;
    }
}
