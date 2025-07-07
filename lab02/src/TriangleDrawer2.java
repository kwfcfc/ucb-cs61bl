public class TriangleDrawer2 {
    private static int SIZE = 5;

    public static void drawTriangle() {
        for (int line = 0; line < SIZE; line++) {
            for (int i = 1; i < line + 1; i++) {
                System.out.print("* ");
            }
            System.out.println("*");
        }
    }

    public static void main(String[] args) {
        drawTriangle();
    }
}