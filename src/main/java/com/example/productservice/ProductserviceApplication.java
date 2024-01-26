package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.stream.Stream;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProductserviceApplication {

//	public static Flux<User> userFlux(){
//		return Flux.just(
//
//				new User(1,"Ganesh","Tadepalli"),
//				new User(2,"sai","Teja"),
//				new User(3,"mohan","krishna")
//
//		);
//	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProductserviceApplication.class, args);

//		Flux<Integer> f=Flux.range(1,10).delayElements(Duration.ofSeconds(1));
//		Mono<String> m= Mono.just("hello");
//		m.subscribe(n -> System.out.println(n), err -> System.out.println(err.getMessage()),()-> System.out.println("Complete"));

//System.out.println(m.block(Duration.ofSeconds(15)));

//
//		List<Integer> list=f.filter(n -> n>10).take(3) .map(n -> n*10).defaultIfEmpty(-1).log().toStream().collect(Collectors.toList());
//
//		System.out.println(list);
//		System.out.println(list.size());
//		f.subscribe(e -> System.out.println(e));
//		f.subscribe(e -> System.out.println(e + " Another One"));
//		System.out.println("Press a key to end");
//		System.in.read();
//
//		Flux<User> f1=userFlux();
//		List<User> list=f1.toStream().collect(Collectors.toList());
//
//		f.flatMap(m -> f1.filter(n -> n.getId() == m)).subscribe(m -> System.out.println(m));

//		System.out.println(list);

//		 List list1= Arrays.asList(1,2,3);
//		 List list2=Arrays.asList(4,5);
//		 List list3=Arrays.asList(6,7,8);
//
//		 List<List<Integer>> finalList=Arrays.asList(list1,list2,list3);
//
//		 List l=finalList.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
//		System.out.println(l);
//		System.out.println(l);


//
	}

}
