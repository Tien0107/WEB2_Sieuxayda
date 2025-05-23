import java.io.PrintStream;
// import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        // Set UTF-8 encoding for console output
        // System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        String[] members = 
        { 
          "Nguyen Mai Duc Tien",
          "Pham Dong Chinh", 
          "Nguyen Van Thinh" 
        };
        System.out.println("Danh sach thanh vien: ");
        System.out.println("Nhóm : ");
        for (int i = 0; i < members.length; i++) {
         System.out.println(members[i]);
        }
    }
}
