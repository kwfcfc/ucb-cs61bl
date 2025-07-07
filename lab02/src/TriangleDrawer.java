public class TriangleDrawer {
    private static int SIZE = 5;

    public static void drawTriangle() {
        int i = 0;
        while (i < SIZE) {
            int count = 1;
            while (count < i + 1) {
                System.out.print("* ");
                count++;
            }
            System.out.println("*");
            i++;
        }
    }

    public static void main(String[] args) {
        drawTriangle();
    }
}