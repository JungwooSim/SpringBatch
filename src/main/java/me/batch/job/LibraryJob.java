package me.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LibraryJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job libraryCreateTable() {
        return jobBuilderFactory.get("LibraryCreateTable")
                .start(libraryCreateTableStep1())
                .build();
    }

    @Bean
    public Step libraryCreateTableStep1() {
        Scanner productData = null;
        File fileName = new File("src/main/resources/library.csv");

        try {
            productData = new Scanner(fileName);

            while (productData.hasNextLine()) {
                String line = productData.nextLine();
                System.out.println(line);
//                String[] lineSplit = line.split("\\,");
//
//                Long id = Long.valueOf(lineSplit[0]);
//                Enum<kindEnum> kind = lineSplit[1].equals("클래스") ? kindEnum.CLASS : kindEnum.KIT;
//                String name = lineSplit[2];
//                Long salePrice = Long.valueOf(lineSplit[3]);
//                Long stock = Long.valueOf(lineSplit[4]);
//
//                Product product = new Product(
//                        id,
//                        kind,
//                        name,
//                        salePrice,
//                        stock
//                );
//
//                products.add(product);
            }

            productData.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (productData != null) {
                productData.close();
            }
        }

        return stepBuilderFactory.get("libraryCreateTableStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>> This is libraryCreateTableStep1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
