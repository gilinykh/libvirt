package com.example.libvirtapi;

import org.libvirt.Connect;
import org.libvirt.NodeInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({LibvirtApiApplicationContext.class})
public class LibvirtApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibvirtApiApplication.class, args);
    }

	@Bean
	public CommandLineRunner createRunner() {

		return args -> {
			Connect conn = new Connect("qemu+ssh://root@vps3.intra/system");
			NodeInfo nodeInfo = conn.nodeInfo();

			System.out.println("model: " + nodeInfo.model + " mem(kb):" + nodeInfo.memory);

			conn.close();
		};
	}
}