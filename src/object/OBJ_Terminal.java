package object;

	import java.io.IOException;

	import javax.imageio.ImageIO;

	public class OBJ_Terminal extends SuperObject {
		
		public OBJ_Terminal() {
			
			name = "Terminal";
			try {
				image = ImageIO.read(getClass().getResource("/objects/terminal.png"));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

}
