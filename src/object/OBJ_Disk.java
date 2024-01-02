package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Disk extends SuperObject {
	
	public OBJ_Disk() {
		
		name = "Floppy Disk";
		try {
			image = ImageIO.read(getClass().getResource("/objects/diskKey.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
