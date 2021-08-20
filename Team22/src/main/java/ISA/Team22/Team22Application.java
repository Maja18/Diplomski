package ISA.Team22;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@SpringBootApplication
public class Team22Application {

	public static void main(String[] args) {
		SpringApplication.run(Team22Application.class, args);
	}

}
