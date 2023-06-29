package dev.mvc.art_v1sbm3c;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.jjim.JjimProcInter;

@SpringBootTest
class ArtV1sbm3cApplicationTests {
  @Autowired
  /*
   * @Qualifier("dev.mvc.jjim.JjimProc") private JjimProcInter jjimProc;
   */
	@Test
	void contextLoads() {
	}
	
  /*
   * @Test void countTest() { int cnt = this.jjimProc.count(1);
   * System.out.println(("-> cnt: " + cnt)); }
   */
}

