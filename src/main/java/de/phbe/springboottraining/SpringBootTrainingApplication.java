package de.phbe.springboottraining;

import de.phbe.springboottraining.core.FileSystem;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.util.unit.DataSize;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootTrainingApplication {

	public static void main(String[] args) {

		// Methode 1
		//SpringApplication.run(SpringBootTrainingApplication.class, args);

		// Methode 2
//		SpringApplication app = new SpringApplication(SpringBootTrainingApplication.class);
//		app.setHeadless(false);
//		app.run(args);

		// Methode 3
//		new SpringApplicationBuilder( SpringBootTrainingApplication.class )
//				.headless( false )
//				.bannerMode( Banner.Mode.OFF )
//				.logStartupInfo( false )
//				.run( args );

		// Methode 4
		// Alle managed beans anzeigen lassen
		ApplicationContext ctx =
				SpringApplication.run( SpringBootTrainingApplication.class, args );
		Arrays.stream( ctx.getBeanDefinitionNames() )
				.sorted()
				.forEach( System.out::println );

		FileSystem fs = ctx.getBean( FileSystem.class );
		System.out.println(
				DataSize.ofBytes( fs.getFreeDiskSpace() ).toGigabytes() + " GB"
		);

	}

}
